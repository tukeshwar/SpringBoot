package com.learning.batch.processing.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import com.learning.batch.processing.model.Product;

@Configuration
public class BatchConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager manager;

    @Bean
    Job job() {
		return new JobBuilder("Job-1", jobRepository).flow(step()).end().build();
		
	}

    @Bean
	Step step() {
    	StepBuilder stepBuilder = new StepBuilder("Step 5", jobRepository).allowStartIfComplete(false);
    	
    	return stepBuilder.<Product, Product>chunk(4, manager).reader(reader()).processor(processor()).writer(writer()).build();
		
	}

	@Bean
	ItemReader<Product> reader() {
		
//		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
//	      reader.setResource(new ClassPathResource("data.csv"));
//	      //reader.setResource(new PathResource(".\\src\\main\\java\\products.csv"));
//	      DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
//	      DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//	      lineTokenizer.setNames("id","name", "description","price");
//	      BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
//	      fieldSetMapper.setTargetType(Product.class);
//	      lineMapper.setLineTokenizer(lineTokenizer);
//	      lineMapper.setFieldSetMapper(fieldSetMapper);
//	      reader.setLineMapper(lineMapper);
//	      return reader;

		
		FlatFileItemReader<Product> flatFileItemReader = new FlatFileItemReader<Product>();
		flatFileItemReader.setResource(new ClassPathResource("data.csv"));

		DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<Product>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"id", "name", "description", "price"});
		

		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<Product>();
		fieldSetMapper.setTargetType(Product.class);
		fieldSetMapper.setStrict(false);
		

		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		flatFileItemReader.setLineMapper(defaultLineMapper);

		return flatFileItemReader;
		

	}

	@Bean
	ItemProcessor<Product, Product> processor() {

		return p -> {
			p.setPrice(p.getPrice() * 0.9);
			return p;
		};

	}
	
	@Bean
	ItemWriter<Product> writer(){
		
		JdbcBatchItemWriter<Product> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		jdbcBatchItemWriter.setSql("INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE) VALUES(:id, :name, :description, :price)");
		
		return jdbcBatchItemWriter;
		
	}

}

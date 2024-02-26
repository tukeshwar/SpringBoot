package com.learning.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.core.service.PaymentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoreApplicationTests {

	@Autowired
	private PaymentServiceImpl service;
	
	@Test
	void testDependancyInjection() {
		assertNotNull(service);
	}

}

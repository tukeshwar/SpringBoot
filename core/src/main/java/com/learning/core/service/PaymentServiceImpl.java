package com.learning.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.learning.core.dao.PaymentDAO;

public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDAO dao;

	public PaymentDAO getDao() {
		return dao;
	}

	public void setDao(PaymentDAO dao) {
		this.dao = dao;
	}
}

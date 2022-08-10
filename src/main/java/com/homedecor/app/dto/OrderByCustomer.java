package com.homedecor.app.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

@Entity
public class OrderByCustomer {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private String status;
	private LocalDate orderDate;
	

	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;
	public OrderByCustomer() {
		super();
		this.orderDate = LocalDate.now();
	}
	
	

	public OrderByCustomer(Integer orderId) {
		super();
		this.orderId = orderId;
		this.orderDate = LocalDate.now();
	}



	public OrderByCustomer(Integer orderId, String status, Payment payment) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.orderDate = LocalDate.now();
		this.payment = payment;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	

}

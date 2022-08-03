package com.example.homedecor.servicetest;


import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Admin;
import com.example.homedecor.dto.Payment;
import com.example.homedecor.exception.AdminException;
import com.example.homedecor.exception.PaymentException;
import com.example.homedecor.service.PaymentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
 class PaymentServiceTest {
	
@Autowired
private PaymentService paymentService;

@AfterEach
void deletePaymentByIdTest() throws PaymentException {
	assertEquals(true,paymentService.deletePaymentById(8));
}

@Test
void addPaymentTest() throws PaymentException {
	Payment payment = new Payment(8,"COD",1000.00,"done");
	assertTrue(paymentService.addPayment(payment));
	assertNotNull(payment != null);
}

@Test
void getPaymentByIdTest() throws PaymentException {
	Payment payment = new Payment(8,"COD",1000.00,"done");
	assertTrue(paymentService.addPayment(payment));
	Payment payment2 = paymentService.getPaymentById(8).get();
	Integer id=payment2.getPaymentId();
	assertEquals(8,id);
	assertNotNull(paymentService.getPaymentById(id));
}


}

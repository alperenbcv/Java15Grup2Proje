package org.example.java15grup2proje.entity;

import org.example.java15grup2proje.entity.enums.EPaymentType;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Compensation {
	Long personnelId;
	EPaymentType paymentType;
	Double amount;
	String currency;
	LocalDate paymentDate;
	boolean isPaid;
	String description;
}
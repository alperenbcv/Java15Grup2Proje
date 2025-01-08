package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EPaymentType;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblcompensation")
public class Compensation extends BaseEntity{
	Long personnelId;
	@Enumerated(EnumType.STRING)
	EPaymentType paymentType;
	Double amount;
	String currency;
	LocalDate paymentDate;
	boolean isPaid;
	String description;
}
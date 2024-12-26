package org.example.java15grup2proje.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "tblcompany")
public class Company extends BaseEntity {
	@Column(unique = true)
	String companyName;
	String companyAddress;
	Long establishedDate;
	String industry;
	Long employeeNumber;
	boolean isPaidMember;
	@Column(unique = true)
	String companyMail;
	String companyLogoUrl;
	String companyWebSite;
	Long membershipPlanId;
	
}
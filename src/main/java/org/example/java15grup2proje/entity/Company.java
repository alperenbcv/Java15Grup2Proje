package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblcompany")
public class Company extends BaseEntity {
	String companyName;
	String companyAddress;
	Long establishedDate;
	String industry;
	Long numberOfEmployee;
	boolean isPaidMember;
	String companyMail;
	String taxNumber;
	String companyLogoUrl;
	String companyWebSite;
	
}
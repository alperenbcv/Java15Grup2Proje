package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblpersonnel")
public class Personnel extends User {
	private Long personnelNumber;
	private String department;
	private String jobTitle;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private Long companyId;
	private Long managerId;
	
	
}
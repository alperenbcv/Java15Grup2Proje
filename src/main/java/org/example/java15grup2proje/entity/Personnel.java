package org.example.java15grup2proje.entity;

import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.ETitle;

import java.time.LocalDate;

public class Personnel extends User {
	private Long personnelNumber;
	private EDepartment department;
	private ETitle jobTitle;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private Long companyId;
	private Long managerId;
	
	
}
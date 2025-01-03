package org.example.java15grup2proje.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ERole;

@Getter
@Setter
public class ProfileResponseDto {
	String name;
	String surname;
	String email;
	String phoneNumber;
	String pictureUrl;
	String address;
	EGender gender;
	boolean isAccountVerified = false;
	boolean isAccountActive = false;
	EDepartment department;
	String title;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private String companyId;
	ERole role;
	
	private String managerId;
	
	boolean isSuperAdmin;
	
}
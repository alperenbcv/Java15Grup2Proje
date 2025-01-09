package org.example.java15grup2proje.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.enums.EState;

@Getter
@Setter
public class EmployeePossessionResponseDto {
	String employeeName;
	String employeeMail;
	String title;
	String description;
	String companyId;
	Long lendingDate;
	Long returnDate;
	EState confirmationState;
}
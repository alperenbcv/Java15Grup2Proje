package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.java15grup2proje.entity.enums.EGender;

import java.time.LocalDate;

public record CompanyRegisterRequestDto(
		@NotNull
		String companyName,
		@Email
		String companyMail,
		@NotNull
		String companyAddress,
		@NotNull
		LocalDate establishedDate,
		@NotNull
		String industry,
		@NotNull
		Long employeeNumber
		
) {
}
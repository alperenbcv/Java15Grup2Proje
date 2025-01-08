package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.java15grup2proje.entity.enums.EDepartment;

public record EmployeeRegisterRequestDto(
		@Email
		String email,
		@NotNull
		@Size(min = 2, max = 22)
		String name,
		@NotNull
		@Size(min = 2, max = 22)
		String surname,
		@NotNull
		EDepartment department,
		@NotNull
		String token
) {
}
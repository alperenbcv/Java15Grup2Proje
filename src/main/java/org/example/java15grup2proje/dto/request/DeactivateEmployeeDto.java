package org.example.java15grup2proje.dto.request;

public record DeactivateEmployeeDto(
		String token,
		String employeeEmail
) {
}
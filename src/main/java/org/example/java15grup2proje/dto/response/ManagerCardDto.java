package org.example.java15grup2proje.dto.response;

import java.time.LocalDate;

public record ManagerCardDto(
		String name,
		String surname,
		String title,
		String phoneNumber,
		String email,
		String department,
		String address,
		LocalDate createdAt
) {
}
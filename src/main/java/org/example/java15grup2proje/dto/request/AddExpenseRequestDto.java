package org.example.java15grup2proje.dto.request;

import jakarta.persistence.Column;

public record AddExpenseRequestDto(
		String title,
		String token,
		Long cost,
		String description
) {
}
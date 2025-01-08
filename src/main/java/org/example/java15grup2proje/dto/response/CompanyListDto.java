package org.example.java15grup2proje.dto.response;

import org.example.java15grup2proje.entity.enums.EState;

public record CompanyListDto(
		String companyName,
		String industry,
		Boolean isPaidMember,
		EState state
) {
}
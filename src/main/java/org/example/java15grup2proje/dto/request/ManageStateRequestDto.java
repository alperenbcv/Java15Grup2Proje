package org.example.java15grup2proje.dto.request;

import org.example.java15grup2proje.entity.enums.EState;

public record ManageStateRequestDto(
		// TODO frontend kısmında field isimlerini düzenle
		String itemId,
		// TODO frontend kısmında field isimlerini düzenle
		String agentToken,
		// TODO frontend kısmında field isimlerini düzenle
		EState updatedState
) {
}
package org.example.java15grup2proje.dto.request;

import org.example.java15grup2proje.entity.enums.EState;

public record ManageCompanyRegisterStateRequestDto (
		String token,
		// Company Id
		String id,
		EState state
){

}
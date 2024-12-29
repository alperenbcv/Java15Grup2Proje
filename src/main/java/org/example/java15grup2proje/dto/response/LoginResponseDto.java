package org.example.java15grup2proje.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.enums.ERole;

@Getter
@Setter
@Builder
public class LoginResponseDto {
	String token;
	ERole role;
}
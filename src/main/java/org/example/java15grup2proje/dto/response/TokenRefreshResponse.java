package org.example.java15grup2proje.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TokenRefreshResponse {
	private String accessToken;
	private String refreshToken;
}
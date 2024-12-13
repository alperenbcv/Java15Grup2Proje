package org.example.java15grup2proje.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenRefreshResponse {
	private String accessToken;
	private String refreshToken;
}
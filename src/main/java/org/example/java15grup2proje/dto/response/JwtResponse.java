package org.example.java15grup2proje.dto.response;

import lombok.AllArgsConstructor;
import org.example.java15grup2proje.entity.User;

@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String refreshToken;
	private User user;
}
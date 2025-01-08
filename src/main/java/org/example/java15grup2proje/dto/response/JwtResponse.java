package org.example.java15grup2proje.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.User;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
	private String token;
	private String refreshToken;
	private User user;
}
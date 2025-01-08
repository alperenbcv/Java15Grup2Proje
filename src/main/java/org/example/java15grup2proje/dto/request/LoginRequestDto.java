package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.*;

public record LoginRequestDto(
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Size(min = 8, max = 64)
		@Pattern(
				message = "Sifre 8 ile 64 karakter arasında olmalıdır. Şifenizde en az bir büçük harf, bir küçük harf ve " +
						"bir özel karakter olmalıdır.",
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&+-_.]).{8,64}$"
		)
		String password
) {
}
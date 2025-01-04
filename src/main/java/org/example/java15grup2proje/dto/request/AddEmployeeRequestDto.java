package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AddEmployeeRequestDto(
		@NotEmpty
		String token,
		@NotEmpty
		String name,
		@NotEmpty
		String surname,
		@NotEmpty
		String email,
		@Pattern(
				message = "Şifreniz en az 8, en fazla 64 karakter olmalıdır. Şifrenizde en az bir büyük harf, bir küçük " +
						"harf ve özel karakter olmalıdır.",
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
		)
		String password,
		@NotEmpty
		String repassword
) {
}
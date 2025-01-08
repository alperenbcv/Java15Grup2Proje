package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.java15grup2proje.entity.enums.EGender;

import java.time.LocalDate;

public record EmployeeActivationRequestDto(
		@NotNull
		EGender gender,
		@NotNull
		@Pattern(
				message = "Şifreniz en az 8, en fazla 64 karakter olmalıdır. Şifrenizde en az bir büyük harf, bir küçük " +
						"harf ve özel karakter olmalıdır.",
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
		)
		@Size(min = 8, max = 64)
		String password,
		@NotNull
		String rePassword,
		@NotNull
		LocalDate birthDate,
		@NotNull
		String title,
		@NotNull
		String activationToken
) {
}
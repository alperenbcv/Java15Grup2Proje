package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ETitle;

import java.time.LocalDate;

public record RegisterRequestDto(
		@NotNull
		String companyName,
		@Email
		String email,
		@NotNull
		@Size(min = 2, max = 22)
		String name,
		@NotNull
		@Size(min = 2, max = 22)
		String surname,
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
		EDepartment department,
		@NotNull
		String title
) {
}
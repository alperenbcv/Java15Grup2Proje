package org.example.java15grup2proje.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	PASSWORD_ERROR(1001, "Girilen şifreler birbiriyle uyuşmamaktadır", HttpStatus.BAD_REQUEST),
	INVALID_USERNAME_OR_PASSWORD(6002,"Kullanıcı adı ya da sifre hatalı.",HttpStatus.BAD_REQUEST)
	
	;
	int code;
	String message;
	HttpStatus httpStatus;
}
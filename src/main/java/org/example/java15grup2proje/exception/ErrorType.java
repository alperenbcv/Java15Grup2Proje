package org.example.java15grup2proje.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	PASSWORD_ERROR(1001, "Girilen şifreler birbiriyle uyuşmamaktadır", HttpStatus.BAD_REQUEST)
	
	
	;
	int code;
	String message;
	HttpStatus httpStatus;
}
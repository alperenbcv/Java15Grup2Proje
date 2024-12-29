package org.example.java15grup2proje.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	PASSWORD_ERROR(1001, "Girilen şifreler birbiriyle uyuşmamaktadır", HttpStatus.BAD_REQUEST),
	
	COMPANY_NOT_FOUND(4001,"No company found with given name.", HttpStatus.NOT_FOUND),
	TOKEN_REFRESH_EXCEPTION(2001, "Refresh token was expired", HttpStatus.BAD_REQUEST),
	INVALID_MAIL_OR_PASSWORD(3001, "mail and password are not matching", HttpStatus.BAD_REQUEST),
	NOT_FOUND_USER(3002, "could not find the given user's data", HttpStatus.BAD_REQUEST),
	ROLE_EXCEPTION(3003,"role name is not registered in the system" , HttpStatus.BAD_REQUEST),
	LEAVE_NOT_FOUND(5001, "could not find the leave with given id", HttpStatus.BAD_REQUEST),
	NO_PERMISSION(1002, "you do not have the permission to execute this method", HttpStatus.BAD_REQUEST);
	
	int code;
	String message;
	HttpStatus httpStatus;
}
package org.example.java15grup2proje.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	INTERNAL_SERVER_ERROR(500,"Unexcepted error has been occurred in server. Please try again!",HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR(400,"Parameters are not valid. Please try again!", HttpStatus.BAD_REQUEST),
	PASSWORD_ERROR(1001, "Girilen şifreler birbiriyle uyuşmamaktadır", HttpStatus.BAD_REQUEST),
	COMPANY_NOT_FOUND(4001,"No company found with given name.", HttpStatus.NOT_FOUND),
	TOKEN_REFRESH_EXCEPTION(2001, "Refresh token was expired", HttpStatus.BAD_REQUEST),
	INVALID_MAIL_OR_PASSWORD(3001, "mail and password are not matching", HttpStatus.BAD_REQUEST),
	ACTIVATON_TOKEN_EXPIRED(2002,"Activation token has expired!",HttpStatus.BAD_REQUEST),
	ACCOUNT_ALREADY_ACTIVE(2003,"Your account has already been activated!",HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(5001,"User not found!",HttpStatus.NOT_FOUND),
	ACCOUNT_NOT_ACTIVE(2004,"Account isn't active!",HttpStatus.FORBIDDEN),
	USER_NOT_AUTHORIZED(5002,"User not authorized!",HttpStatus.UNAUTHORIZED),
	SHIFT_NOT_FOUND(6000,"No shift found with the given id",HttpStatus.NOT_FOUND),
	SHIFT_ALREADY_EXISTS(6001,"A shift already has been created for given day",HttpStatus.BAD_REQUEST);
	int code;
	String message;
	HttpStatus httpStatus;
}
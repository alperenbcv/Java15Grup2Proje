package org.example.java15grup2proje.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	public ResponseEntity<ErrorMessage> runtimeExcepitonHandler(RuntimeException exception){
		return  createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@ExceptionHandler(Java15Grup2ProjeAppException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> java15Grup2AppExceptionHandler(Java15Grup2ProjeAppException exception){
		return createResponseEntity(exception.getErrorType(),exception.getErrorType().getHttpStatus(),null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		List<String> fieldErrors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
		         .forEach(f->{
			         //  f.getField() -> hata fırlatan nesnenin değişken adı örn: email
			         //  f.getDefaultMessage() -> hataya ait detay bilgisi örn: geçerli bir email giriniz.
			         fieldErrors.add("Değişken adı..: "+ f.getField()+ " - Hata Detayı...: "+ f.getDefaultMessage());
		         });
		return createResponseEntity(ErrorType.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,fieldErrors);
	}
	
	
	
	public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, HttpStatus httpStatus, List<String> fields){
		log.error("TÜM HATALARIN GEÇTİĞİ NOKTA....: "+ errorType.getMessage()+ fields);
		return new ResponseEntity<>(ErrorMessage.builder()
		                                        .fields(fields)
		                                        .success(false)
		                                        .message(errorType.getMessage())
		                                        .code(errorType.getCode())
		                                        .build(),httpStatus);
	}
}
package org.example.java15grup2proje.exception;

public class Java15Grup2ProjeAppException extends RuntimeException {
	private ErrorType errorType;
	public Java15Grup2ProjeAppException(ErrorType errorType){
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
package org.example.java15grup2proje.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ErrorMessage {
	int code;
	String message;
	Boolean success;
	List<String> fields;
}
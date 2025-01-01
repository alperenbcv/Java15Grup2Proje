package org.example.java15grup2proje.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.Expense;

@Builder
@Getter
@Setter
public class ExpenseManagerResponseDto {
	Expense expense;
	String employeeName;
}
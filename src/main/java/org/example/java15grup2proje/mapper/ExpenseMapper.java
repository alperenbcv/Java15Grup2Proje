package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.request.AddExpenseRequestDto;
import org.example.java15grup2proje.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
	ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);
	
	
	Expense dtoToExpense(AddExpenseRequestDto dto, String personnelId, String managerId);
}
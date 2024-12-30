package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ManageStateRequestDto;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.Expense;
import org.example.java15grup2proje.entity.Possession;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.AuthRepository;
import org.example.java15grup2proje.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
	private final ExpenseRepository expenseRepository;
	private final AuthService authService;
	private final AuthRepository authRepository;
	
	public void manageExpense(@Valid ManageStateRequestDto dto) {
		Auth auth = authService.tokenToAuth(dto.agentToken());
		if (auth.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		String expenseId = dto.itemId();
		Optional<Expense> optExpense = expenseRepository.findById(expenseId);
		if (optExpense.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.POSSESSION_NOT_FOUND);
		Expense expense = optExpense.get();
		if (!auth.getId().equals(expense.getManagerId()))
			throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		expense.setExpenseState(dto.updatedState());
		expenseRepository.save(expense);
	}
	
	public List<Expense> getMyExpenses(String token) {
		Auth auth = authService.tokenToAuth(token);
		List<Expense> expenseList = expenseRepository.findAllByPersonnelId(auth.getId());
		return expenseList;
	}
}
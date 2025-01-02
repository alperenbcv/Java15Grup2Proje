package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.AddExpenseRequestDto;
import org.example.java15grup2proje.dto.request.ManageStateRequestDto;
import org.example.java15grup2proje.dto.response.ExpenseManagerResponseDto;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.ExpenseMapper;
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
	private final UserService userService;
	private final EmployeeService employeeService;
	
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
	
	public List<ExpenseManagerResponseDto> getMyEmployeesExpenses(String token) {
		String managerId = authService.tokenToAuthId(token);
		List<Expense> expenseList = expenseRepository.findAllByManagerId(managerId);
		return expenseList.stream().map(expense ->{
			Employee employee = employeeService.findById(expense.getPersonnelId());
			String employeeName = employee.getName() + " " + employee.getSurname();
			return ExpenseManagerResponseDto.builder()
					.employeeName(employeeName)
					.expense(expense).build();
		}).toList();
	}
	
	public void addExpense(@Valid AddExpenseRequestDto dto) {
		User user = userService.tokenToUser(dto.token());
		if (user.getRole() != ERole.EMPLOYEE) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		Employee employee = (Employee) user;
		Expense expense = ExpenseMapper.INSTANCE.dtoToExpense(dto, employee.getId(), employee.getManagerId());
		expenseRepository.save(expense);
	}
	
	public Optional<Expense> findById(String s) {
		return expenseRepository.findById(s);
	}
	
	public void save(Expense expense) {
		expenseRepository.save(expense);
	}
}
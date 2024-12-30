package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ManageStateRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.Expense;
import org.example.java15grup2proje.entity.Possession;
import org.example.java15grup2proje.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(EXPENSE)
public class ExpenseController {
	private final ExpenseService expenseService;
	
	@PostMapping(MANAGE_EXPENSE)
	public ResponseEntity<BaseResponse<Boolean>> getPendingLeaves(@RequestBody @Valid ManageStateRequestDto dto){
		expenseService.manageExpense(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .data(true)
		                                     .message("Expense managed")
		                                     .success(true).build());
		
	}
	
	@GetMapping(GET_MY_EXPENSES)
	public ResponseEntity<BaseResponse<List<Expense>>> getMyExpenses(String token){
		return ResponseEntity.ok(BaseResponse.<List<Expense>>builder()
		                                     .code(200)
		                                     .data(expenseService.getMyExpenses(token))
		                                     .message("Fetched the expense of the employee")
		                                     .success(true).build());
		
	}
}
package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.*;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_EMPLOYEE)
@CrossOrigin("*")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		String token = employeeService.employeeLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Employee login " +
				                                                                                        "successful.")
		                                     .data(token).build());
		
	}
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid EmployeeRegisterRequestDto dto){
		
		employeeService.employeeRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Employee register " +
				                                                                                      "successful" + ".").success(true).build());
	}
	
	@PutMapping(ACTIVATE_EMPLOYEE_ACC)
	public ResponseEntity<BaseResponse<Boolean>> activateEmployeeAccount(@RequestBody @Valid EmployeeActivationRequestDto dto){
		if(!dto.password().equals(dto.rePassword()))
			throw new Java15Grup2ProjeAppException(ErrorType.PASSWORD_ERROR);
		employeeService.employeeActivateAccount(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().success(true).data(true).message("Account activation successful!").code(200).build());
	}
	
	@PutMapping(DELETE_EMPLOYEE)
	public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@RequestBody DeleteEmployeeRequestDto dto){
		employeeService.deleteEmployee(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().success(true).data(true).message("Employee deleted successfully!").code(200).build());
	}
}
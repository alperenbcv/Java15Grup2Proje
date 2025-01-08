package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.*;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_EMPLOYEE)
@CrossOrigin("*")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid EmployeeRegisterRequestDto dto){
		
		employeeService.employeeRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Employee register " +
				                                                                                      "successful" + ".").success(true).build());
	}
	
	@GetMapping(GET_MY_PERSONNEL)
	public ResponseEntity<BaseResponse<List<Employee>>> getPersonnelByCompanyId(String token){
		return ResponseEntity.ok(BaseResponse.<List<Employee>>builder()
				                         .success(true)
				                         .message("yöneticinin çalışanları başarıyla getirildi")
				                         .code(200)
				                         .data(employeeService.getPersonnelByCompanyId(token))
		                                     .build());
	}
	
	@PutMapping(ACTIVATE_EMPLOYEE_ACC)
	public ResponseEntity<BaseResponse<Boolean>> activateEmployeeAccount(@RequestBody @Valid EmployeeActivationRequestDto dto){
		if(!dto.password().equals(dto.rePassword()))
			throw new Java15Grup2ProjeAppException(ErrorType.PASSWORD_ERROR);
		employeeService.employeeActivateAccount(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().success(true).data(true).message("Account activation successful!").code(200).build());
	}
	
	@PostMapping(EDIT_MY_PERSONNEL)
	public ResponseEntity<BaseResponse<Boolean>> editMyPersonnel(@RequestBody @Valid EditMyPersonnelRequestDto dto){
		//TODO burası yazılacak
		return null;
	}
}
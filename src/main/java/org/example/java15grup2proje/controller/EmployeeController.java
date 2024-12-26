package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EditMyPersonnelRequestDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_EMPLOYEE)
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
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto){
		
		if(!dto.password().equals(dto.rePassword()))
			throw new Java15Grup2ProjeAppException(ErrorType.PASSWORD_ERROR);
		employeeService.employeeRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Employee register " +
				                                                                                      "successful" +
				                                                                                      ".").success(true).build());
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
	
	@PostMapping(EDIT_MY_PERSONNEL)
	public ResponseEntity<BaseResponse<Boolean>> editMyPersonnel(@RequestBody @Valid EditMyPersonnelRequestDto dto){
		//TODO burası yazılacak
		return null;
	}
}
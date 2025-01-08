package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.PasswordRecoveryRequestDto;
import org.example.java15grup2proje.dto.response.EmployeeListDto;
import org.example.java15grup2proje.dto.response.ManagerCardDto;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_MANAGER)
@CrossOrigin("*")
public class ManagerController {
	private final ManagerService managerService;
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		String token = managerService.managerLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Manager login " +
				                                                                                        "successful.")
		                                     .data(token).build());
		
	}
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto){
		if(!dto.password().equals(dto.rePassword()))
			throw new Java15Grup2ProjeAppException(ErrorType.PASSWORD_ERROR);
		managerService.managerRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Manager register " +
				                                                                                      "successful" +
				                                                                                      ".").success(true).build());
	}
	
	@PutMapping(ACCOUNT_ACTIVATION)
	public ResponseEntity<BaseResponse<Boolean>> activateAccount(@RequestParam String token) {
		boolean isActivated = managerService.activateAccount(token);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(isActivated ? 200 : 400).data(isActivated)
		                                     .message(isActivated ? "Account activation successful!" : "Activation token has expired!")
		                                     .build());
	}
	
	@PostMapping(GET_ALL_EMPLOYEE_LIST)
	public ResponseEntity<BaseResponse<List<EmployeeListDto>>> getAllEmployeeList(@RequestBody String token){
		return ResponseEntity.ok(BaseResponse.<List<EmployeeListDto>>builder().code(200).data(managerService.getAllEmployeeListByCompanyId(token))
		                                     .message("Employee list brought successfully!").success(true).build());
	}
	
	@PostMapping(GET_MANAGER_CARD)
	public ResponseEntity<BaseResponse<ManagerCardDto>> getManagerCard(@RequestBody String token){
		return ResponseEntity.ok(BaseResponse.<ManagerCardDto>builder().code(200).data(managerService.getManagerCardDto(token)).message("Manager card brought successfully!").success(true).build());
	}
	
	@PostMapping(SEND_RECOVERY_MAIL)
	public ResponseEntity<BaseResponse<Boolean>> sendRecoveryMail(@RequestBody String mail){
		managerService.sendRecoveryMail(mail);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Recovery mail has " +
				                                                                                      "been sent!").success(true).build());
	}
	
	@PostMapping(PASSWORD_RECOVERY)
	public ResponseEntity<BaseResponse<Boolean>> passwordRecovery(@RequestBody @Valid PasswordRecoveryRequestDto dto){
		managerService.passwordRecovery(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Password updated " +
				                                                                                      "successfully!").success(true).build());
	}
}
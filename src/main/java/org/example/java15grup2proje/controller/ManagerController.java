package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.*;
import org.example.java15grup2proje.dto.response.TokenRefreshResponse;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.RefreshToken;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.ManagerService;
import org.example.java15grup2proje.service.RefreshTokenService;
import org.example.java15grup2proje.utility.JwtManager;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_MANAGER)
@CrossOrigin("*")
public class ManagerController {
	private final ManagerService managerService;
	private final RefreshTokenService refreshTokenService;
	private final JwtManager jwtManager;
	
	
	
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
package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.AdminRegisterRequestDto;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_ADMIN)
public class AdminController {
	private final AdminService adminService;
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		String token = adminService.adminLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Admin login successful.")
		                                     .data(token).build());
		
	}
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid AdminRegisterRequestDto dto){
		adminService.adminRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Admin register successful.").success(true).build());
	}
}
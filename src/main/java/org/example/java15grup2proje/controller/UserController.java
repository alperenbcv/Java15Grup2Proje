package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.example.java15grup2proje.constant.RestApi.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
	private final UserService userService;
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto) {
		if (!dto.password().equals(dto.repassword())) throw new Java15Grup2ProjeAppException(ErrorType.PASSWORD_ERROR);
		userService.register(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200)
		                                     .message("Kayıt işlemleri başarıyla tamamlandı").success(true).data(true)
		                                     .build());
	}
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		
			String token = userService.login(dto);
			return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Giriş başarılı.")
			                                     .data(token).build());
		
	}
}
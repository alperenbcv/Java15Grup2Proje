package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EditProfileDto;
import org.example.java15grup2proje.dto.request.TokenRefreshRequest;
import org.example.java15grup2proje.dto.response.TokenRefreshResponse;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.RefreshToken;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
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
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		String token = managerService.managerLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Manager login " +
				                                                                                        "successful.")
		                                     .data(token).build());
		
	}
	
	@PostMapping(REFRESH_TOKEN)
	public ResponseEntity<BaseResponse<TokenRefreshResponse>> refreshToken(@Valid @RequestBody TokenRefreshRequest dto){
		String requestRefreshToken = dto.refreshToken();
		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUserId)
				.map(userId -> {
					String token = jwtManager.createToken(userId, "refresh");
					return ResponseEntity.ok(BaseResponse.<TokenRefreshResponse>builder().code(200).success(true).message("refresh " +
							                                                                                    "token " +
							                                                                                 "successfully created")
							                         .data(TokenRefreshResponse.builder().accessToken(token).refreshToken(requestRefreshToken).build()).build());
				}).orElseThrow(()->new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION));
		
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
	
	@GetMapping(GET_PROFILE)
	public ResponseEntity<BaseResponse<Manager>> getProfile(String token){
		
		Manager manager = managerService.getProfile(token);
		return ResponseEntity.ok(BaseResponse.<Manager>builder().code(200).data(manager).message("Manager register " +
				                                                                                      "successful" +
				                                                                                      ".").success(true).build());
	}
	
	@PostMapping(EDIT_PROFILE)
	public ResponseEntity<BaseResponse<Manager>> editProfile(@RequestBody @Valid EditProfileDto dto){
		return ResponseEntity.ok(BaseResponse.<Manager>builder().code(200)
				                         .success(true)
		                                     .data(managerService.editProfile(dto))
		                                     .message("Manager register " +
				                                                                                         "successful" +
				                                                                                         ".").success(true).build());
	}
	
	@GetMapping(EDIT_PHOTO)
	public ResponseEntity<BaseResponse<Manager>> editProfilePhoto(String token, String photoUrl){
		
		Manager manager = managerService.editPhoto(token, photoUrl);
		return ResponseEntity.ok(BaseResponse.<Manager>builder().code(200).data(manager).message("Manager register " +
				                                                                                         "successful" +
				                                                                                         ".").success(true).build());
	}
	
	
	
}
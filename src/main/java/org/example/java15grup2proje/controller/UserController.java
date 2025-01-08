package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.*;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.dto.response.TokenRefreshResponse;
import org.example.java15grup2proje.entity.RefreshToken;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.service.RefreshTokenService;
import org.example.java15grup2proje.service.UserService;
import org.example.java15grup2proje.utility.JwtManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin("*")
public class UserController {
	private final RefreshTokenService refreshTokenService;
	private final JwtManager jwtManager;
	private final UserService userService;
	
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).success(true).message("Manager login " +
				                                                                                        "successful.")
		                                     .data(userService.login(dto)).build());
		
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
	
	@GetMapping(GET_PROFILE)
	public ResponseEntity<BaseResponse<ProfileResponseDto>> getProfile(String token){
		
		ProfileResponseDto dto = userService.getProfile(token);
		return ResponseEntity.ok(BaseResponse.<ProfileResponseDto>builder().code(200).data(dto).message("Manager " +
				                                                                                             "register " +
				                                                                                         "successful" +
				                                                                                         ".").success(true).build());
	}
	
	@PostMapping(EDIT_PROFILE)
	public ResponseEntity<BaseResponse<ProfileResponseDto>> editProfile(@RequestBody @Valid EditProfileDto dto){
		return ResponseEntity.ok(BaseResponse.<ProfileResponseDto>builder().code(200)
		                                     .success(true)
		                                     .data(userService.editProfile(dto))
		                                     .message("Manager register " +
				                                              "successful" +
				                                              ".").success(true).build());
	}
	
	@GetMapping(EDIT_PHOTO)
	public ResponseEntity<BaseResponse<ProfileResponseDto>> editProfilePhoto(String token, String photoUrl){
		
		ProfileResponseDto profile = userService.editPhoto(token, photoUrl);
		return ResponseEntity.ok(BaseResponse.<ProfileResponseDto>builder().code(200).data(profile).message("Manager register " +
				                                                                                         "successful" +
				                                                                                         ".").success(true).build());
	}
	
	@GetMapping(GET_MANAGER_BY_COMMENT)
	public ResponseEntity<BaseResponse<ProfileResponseDto>> getManagerByComment(String commentId){
		
		ProfileResponseDto profile = userService.getManagerByComment(commentId);
		return ResponseEntity.ok(BaseResponse.<ProfileResponseDto>builder().code(200).data(profile).message("Manager register " +
				                                                                                                    "successful" +
				                                                                                                    ".").success(true).build());
	}
	
	@GetMapping(GET_MY_EMPLOYEES)
	public ResponseEntity<BaseResponse<List<ProfileResponseDto>>> getMyEmployees(String token){
		
		List<ProfileResponseDto> myEmployees = userService.getMyEmployees(token);
		return ResponseEntity.ok(BaseResponse.<List<ProfileResponseDto>>builder()
		                                     .code(200).data(myEmployees)
                                                .message("Manager register " +
                                                                    "successful" +
                                                                    ".").success(true).build());
	}
	
	@PostMapping(ADD_EMPLOYEE)
	public ResponseEntity<BaseResponse<Boolean>> addEmployee(@RequestBody @Valid AddEmployeeRequestDto dto){
		userService.addEmployee(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200).data(true)
		                                     .message("Manager register " +
				                                              "successful" +
				                                              ".").success(true).build());
	}
	
	@PostMapping(DEACTIVATE_EMPLOYEE)
	public ResponseEntity<BaseResponse<Boolean>> deactivateEmployee(@RequestBody DeactivateEmployeeDto dto){
		userService.deactivateEmployeeByEmail(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200).data(true)
		                                     .message("employee deleted successfully").success(true).build());
	}
	
	@PostMapping(ALTER_ACCOUNT_ACTIVATION)
	public ResponseEntity<BaseResponse<Boolean>> alterAccountActivation(@RequestBody DeactivateEmployeeDto dto){
		userService.alterAccountActivation(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200).data(true)
		                                     .message("employee activation altered successfully").success(true).build());
	}
}
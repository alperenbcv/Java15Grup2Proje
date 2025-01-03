package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.TokenRefreshRequest;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.dto.response.LoginResponseDto;
import org.example.java15grup2proje.dto.response.TokenRefreshResponse;
import org.example.java15grup2proje.entity.RefreshToken;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.service.AuthService;
import org.example.java15grup2proje.service.RefreshTokenService;
import org.example.java15grup2proje.utility.JwtManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@CrossOrigin("*")
public class AuthController {
	private final RefreshTokenService refreshTokenService;
	private final AuthService authService;
	private final JwtManager jwtManager;
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<LoginResponseDto>> doLogin(@RequestBody @Valid LoginRequestDto dto) {
		return ResponseEntity.ok(BaseResponse.<LoginResponseDto>builder().code(200).success(true).message("Manager login " +
				                                                                                        "successful.")
		                                     .data(authService.login(dto)).build());
		
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
}
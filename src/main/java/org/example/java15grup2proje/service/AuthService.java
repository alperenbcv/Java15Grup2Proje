package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.response.LoginResponseDto;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.UserMapper;
import org.example.java15grup2proje.repository.AuthRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthRepository authRepository;
	private final JwtManager jwtManager;
	private final ManagerService managerService;
	private final EmployeeService employeeService;
	private final AdminService adminService;
	private final RefreshTokenService refreshTokenService;
	
	public ProfileResponseDto tokenToProfile(String token){
		Auth auth = tokenToAuth(token);
		return userToProfile(auth);
	}
	
	public ProfileResponseDto userToProfile(Auth auth){
		switch (auth.getRole()){
			case MANAGER -> {
				Optional<Manager> optManager = managerService.findById(auth.getId());
				if (optManager.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
				Manager manager = optManager.get();
				return UserMapper.INSTANCE.fromManagerToProfile(manager);
			}
			case EMPLOYEE -> {
				Employee employee = employeeService.findById(auth.getId());
				return UserMapper.INSTANCE.fromEmployeeToProfile(employee);
			}
			case ADMIN -> {
				Admin admin = adminService.findById(auth.getId());
				return UserMapper.INSTANCE.fromAdminToProfile(admin);
			}
		}
		throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
	}
	
	public Auth tokenToAuth(String token){
		String userId = tokenToAuthId(token);
		return authIdToAuth(userId);
	}
	
	public String tokenToAuthId(String token){
		Optional<String> optAuthId = jwtManager.validateToken(token);
		if(optAuthId.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		return optAuthId.get();
	}
	
	public Auth authIdToAuth(String authId){
		Optional<Auth> optAuth = authRepository.findById(authId);
		if (optAuth.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return optAuth.get();
	}
	
	public LoginResponseDto login(@Valid LoginRequestDto dto) {
		Optional<Auth> optAuth = authRepository.findByEmail(dto.email());
		if (optAuth.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		Auth auth = optAuth.get();
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), auth.getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		if (!auth.isAccountActive()) return LoginResponseDto.builder().token("notActive").build();
		String token = jwtManager.createToken(auth.getId(), auth.getRole().toString());
		return LoginResponseDto.builder().role(auth.getRole()).token(token).build();
		
	}
}
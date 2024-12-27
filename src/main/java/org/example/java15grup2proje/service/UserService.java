package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EditProfileDto;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.entity.Admin;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.UserMapper;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	private final ManagerService managerService;
	private final EmployeeService employeeService;
	private final AdminService adminService;
	
	public ProfileResponseDto tokenToProfile(String token){
		User user = tokenToUser(token);
		return userToProfile(user);
	}
	
	public ProfileResponseDto userToProfile(User user){
		switch (user.getRole()){
			case MANAGER -> {
				Manager manager = managerService.findById(user.getId());
				return UserMapper.INSTANCE.fromManagerToProfile(manager);
			}
			case EMPLOYEE -> {
				Employee employee = employeeService.findById(user.getId());
				return UserMapper.INSTANCE.fromEmployeeToProfile(employee);
			}
			case ADMIN -> {
				Admin admin = adminService.findById(user.getId());
				return UserMapper.INSTANCE.fromAdminToProfile(admin);
			}
		}
		throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
	}
	
	public User tokenToUser(String token){
		Optional<String> optUserId = jwtManager.validateToken(token);
		if(optUserId.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		Optional<User> optUser = userRepository.findById(optUserId.get());
		if (optUser.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return optUser.get();
	}
	
	public String login(@Valid LoginRequestDto dto) {
		Optional<User> optionalUser = userRepository.findByEmail(dto.email());
		if (optionalUser.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), optionalUser.get().getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		String token = jwtManager.createToken(optionalUser.get().getId(), optionalUser.get().getRole().toString());
		
		return token;
	}
	
	public ProfileResponseDto getProfile(String token) {
		return tokenToProfile(token);
	}
	
	public ProfileResponseDto editProfile(@Valid EditProfileDto dto) {
		ProfileResponseDto profile = tokenToProfile(dto.token());
		UserMapper.INSTANCE.fromEditToProfile(dto, profile);
		return profile;
	}
	
	public ProfileResponseDto editPhoto(String token, String photoUrl) {
		User user = tokenToUser(token);
		user.setPictureUrl(photoUrl);
		user = userRepository.save(user);
		return userToProfile(user);
	}
}
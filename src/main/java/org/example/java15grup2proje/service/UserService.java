package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EditProfileDto;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.UserMapper;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	private final ManagerService managerService;
	private final EmployeeService employeeService;
	private final AdminService adminService;
	private final CommentService commentService;
	
	public UserService(UserRepository userRepository, JwtManager jwtManager,
	                   ManagerService managerService, EmployeeService employeeService,
	                   AdminService adminService, @Lazy CommentService commentService) {
		this.userRepository = userRepository;
		this.jwtManager = jwtManager;
		this.managerService = managerService;
		this.employeeService = employeeService;
		this.adminService = adminService;
		this.commentService = commentService;
	}
	
	public ProfileResponseDto tokenToProfile(String token){
		User user = tokenToUser(token);
		return userToProfile(user);
	}
	
	public ProfileResponseDto userToProfile(User user){
		switch (user.getRole()){
			case MANAGER -> {
				Optional<Manager> optManager = managerService.findById(user.getId());
				if (optManager.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
				Manager manager = optManager.get();
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
		String userId = tokenToUserId(token);
		return userIdToUser(userId);
	}
	
	public String tokenToUserId(String token){
		Optional<String> optUserId = jwtManager.validateToken(token);
		if(optUserId.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		return optUserId.get();
	}
	
	public User userIdToUser(String userId){
		Optional<User> optUser = userRepository.findById(userId);
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
		User user = tokenToUser(dto.token());
		UserMapper userMapper = UserMapper.INSTANCE;
		userMapper.fromEditToUser(dto, user);
		userRepository.save(user);
		ProfileResponseDto profile = userToProfile(user);
		return profile;
	}
	
	public ProfileResponseDto editPhoto(String token, String photoUrl) {
		User user = tokenToUser(token);
		user.setPictureUrl(photoUrl);
		user = userRepository.save(user);
		return userToProfile(user);
	}
	
	public ProfileResponseDto getManagerByComment(String commentId) {
		Optional<Comment> optComment = commentService.findById(commentId);
		if (optComment.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMMENT_NOT_FOUND);
		String managerId = optComment.get().getManagerId();
		ProfileResponseDto profile = userToProfile(userIdToUser(managerId));
		if (profile.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		return profile;
	}
}
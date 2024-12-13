package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.UserMapper;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	
	public User register(RegisterRequestDto dto) {
		return userRepository.save(UserMapper.INSTANCE.fromRegisterRequestDto(dto));
	}
	
	public String login(@Valid LoginRequestDto dto) {
		Optional<User> userOptional = userRepository.findByUserNameAndPassword(dto.userName(), dto.password());
		if (userOptional.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
		String token = jwtManager.createToken(userOptional.get().getId());
		
		return token;
	}
}
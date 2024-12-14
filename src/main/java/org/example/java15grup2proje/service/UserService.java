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

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtManager jwtManager;
	
	public User register(RegisterRequestDto dto) {
		try{
			return userRepository.save(UserMapper.INSTANCE.fromRegisterRequestDto(dto));
		}
		catch (NoSuchAlgorithmException e) {
			// TODO nosuchalgorithmException yaz
			throw new RuntimeException();
		}
	}
	
	public String login(@Valid LoginRequestDto dto) {
		Optional<User> userOptional = userRepository.findByEmailAndPassword(dto.email(), dto.password());
		if (userOptional.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		String token = jwtManager.createToken(userOptional.get().getId());
		
		return token;
	}
}
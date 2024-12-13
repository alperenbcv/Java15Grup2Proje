package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.mapper.UserMapper;
import org.example.java15grup2proje.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public User register(RegisterRequestDto dto) {
		return userRepository.save(UserMapper.INSTANCE.fromRegisterRequestDto(dto));
	}
}
package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	
}
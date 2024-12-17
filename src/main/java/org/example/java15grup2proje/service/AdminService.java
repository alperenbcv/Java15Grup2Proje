package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exception.ErrorType;
import org.example.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.AdminRegisterRequestDto;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.entity.Admin;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.repository.AdminRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.example.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final JwtManager jwtManager;
	private final AdminRepository adminRepository;
	
	public String adminLogin(@Valid LoginRequestDto dto) {
		Optional<Admin> optionalAdmin = adminRepository.findAdminByEmail(dto.email());
		if (optionalAdmin.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), optionalAdmin.get().getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		String token = jwtManager.createToken(optionalAdmin.get().getId(),optionalAdmin.get().getRole().toString());
		return token;
	}
	
	public void adminRegister(@Valid AdminRegisterRequestDto dto){
		Admin admin = AdminMapper.INSTANCE.fromAdminRegisterDto(dto);
		admin.setRole(ERole.ADMIN);
		adminRepository.save(admin);
	}
}
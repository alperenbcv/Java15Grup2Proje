package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.repository.ManagerRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.example.java15grup2proje.mapper.ManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@CrossOrigin("*")
public class ManagerService {
	private final ManagerRepository managerRepository;
	private final JwtManager jwtManager;
	private final CompanyService companyService;
	
	
	public void managerRegister(@Valid RegisterRequestDto dto){
		Manager manager = ManagerMapper.INSTANCE.fromRegisterRequestDto(dto);
		manager.setRole(ERole.MANAGER);
		manager.setCompanyId(companyService.findCompanyIdByCompanyName(dto.companyName()));
		managerRepository.save(manager);
	}
	
	public String managerLogin(@Valid LoginRequestDto dto) {
		Optional<Manager> optionalManager = managerRepository.findManagerByEmail(dto.email());
		if (optionalManager.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), optionalManager.get().getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		String token = jwtManager.createToken(optionalManager.get().getId(),optionalManager.get().getRole().toString());
		return token;
	}
	
	public Manager getProfile(@Valid String token) {
		Optional<String> optManagerId = jwtManager.validateToken(token);
		if(optManagerId.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		Optional<Manager> optManager = managerRepository.findById(optManagerId.get());
		if (optManager.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return optManager.get();
 	}
}
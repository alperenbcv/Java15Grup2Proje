package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EditProfileDto;
import org.example.java15grup2proje.dto.request.PasswordRecoveryRequestDto;
import org.example.java15grup2proje.dto.response.EmployeeListDto;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CrossOrigin("*")
public class ManagerService {
	private final ManagerRepository managerRepository;
	private final JwtManager jwtManager;
	private final CompanyService companyService;
	private final MailService mailService;
	
	public Manager tokenToManager(String token){
		Optional<String> optManagerId = jwtManager.validateToken(token);
		if(optManagerId.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		Optional<Manager> optManager = managerRepository.findById(optManagerId.get());
		if (optManager.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return optManager.get();
	}
	public void managerRegister(@Valid RegisterRequestDto dto){
		Manager manager = ManagerMapper.INSTANCE.fromRegisterRequestDto(dto);
		String activationToken = jwtManager.createActivationToken();
		manager.setActivationToken(activationToken);
		manager.setRole(ERole.MANAGER);
		manager.setCompanyId(companyService.findCompanyIdByCompanyName(dto.companyName()));
		managerRepository.save(manager);
		mailService.sendManagerActivationMail(dto.email(), activationToken);
	}
	
	public String managerLogin(@Valid LoginRequestDto dto) {
		Manager manager = managerRepository.findManagerByEmail(dto.email())
		                                   .orElseThrow(() -> new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD));
		
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), manager.getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		
		if (!manager.isAccountActive()) {
			throw new Java15Grup2ProjeAppException(ErrorType.ACCOUNT_NOT_ACTIVE);
		}
		
		String token = jwtManager.createToken(manager.getId(), manager.getRole().toString());
		return token;
	}
	
	public Manager getProfile(@Valid String token) {
		return tokenToManager(token);
 	}
	
	public Manager editProfile(@Valid EditProfileDto dto) {
		Manager manager = tokenToManager(dto.token());
		//TODO mapper ile vs iyileştirilebiliyor mu araştır
		manager.setEmail(dto.email());
		manager.setPhoneNumber(dto.phoneNumber());
		manager.setAddress(dto.address());
		manager.setGender(dto.gender());
		Manager manager2 = managerRepository.save(manager);
		return manager2;
	}
	
	public boolean activateAccount(String token){
		if (!jwtManager.validateActivationToken(token)) {
			throw new Java15Grup2ProjeAppException(ErrorType.ACTIVATION_TOKEN_EXPIRED);
		}
		Manager manager = managerRepository.findManagerByActivationToken(token)
		                                   .orElseThrow(() -> new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER));
		if (manager.isAccountActive()) {
			throw new Java15Grup2ProjeAppException(ErrorType.ACCOUNT_ALREADY_ACTIVE);
		}
		manager.setAccountActive(true);
		manager.setAccountVerified(true);
		manager.setActivationToken(null);
		managerRepository.save(manager);
		
		return true;
	}
	
	public boolean managerExistsById(String id){
		return managerRepository.existsById(id);
	}
	
	public String getCompanyIdByManagerId(String id){
		return managerRepository.findCompanyIdByManagerId(id);
	}
	
	public Manager editPhoto(String token, String photoUrl) {
		Manager manager = tokenToManager(token);
		manager.setPictureUrl(photoUrl);
		return managerRepository.save(manager);
	}
	
	
	public void sendRecoveryMail(String mail){
		if(!managerRepository.existsByEmail(mail))
			throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		mailService.sendPasswordRecoveryMail(mail);
	}
	
	public void passwordRecovery(PasswordRecoveryRequestDto dto){
		Optional<Manager> optionalManager = managerRepository.findManagerByEmail(dto.mail());
		if(optionalManager.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		}
		Manager manager = optionalManager.get();
		manager.setPassword(PasswordHasher.passwordHash(dto.password()));
		managerRepository.save(manager);
	}
	
	public Optional<Manager> findById(String userId) {
		return managerRepository.findById(userId);
	}
}
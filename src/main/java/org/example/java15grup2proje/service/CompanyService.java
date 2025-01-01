package org.example.java15grup2proje.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ManageCompanyRegisterStateRequestDto;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.CompanyRegisterRequestDto;
import org.example.java15grup2proje.entity.Company;
import org.example.java15grup2proje.repository.CompanyRepository;
import org.example.java15grup2proje.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;
	private final AuthService authService;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository,@Lazy AuthService authService) {
		this.companyRepository = companyRepository;
		this.authService = authService;
	}
	
	public void companyRegister(@Valid CompanyRegisterRequestDto dto){
		Company company = CompanyMapper.INSTANCE.fromCompanyRegisterRequestDto(dto);
		companyRepository.save(company);
	}
	
	public String findCompanyIdByCompanyName(String companyName){
		Optional<Company> optionalCompany= companyRepository.findCompanyByCompanyName(companyName);
		if(optionalCompany.isPresent()){
			return optionalCompany.get().getId();
		}
		else{
			throw new Java15Grup2ProjeAppException(ErrorType.COMPANY_NOT_FOUND);
		}
	}
	
	public List<String> getAllCompanyNames(){
		return companyRepository.getCompanyNames();
	}
	
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
	
	public void manageCompanyRegisterState(@Valid ManageCompanyRegisterStateRequestDto dto) {
		Auth auth = authService.tokenToAuth(dto.token());
		if (auth.getRole() != ERole.ADMIN) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		Optional<Company> optCompany = companyRepository.findById(dto.id());
		if (optCompany.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.COMPANY_NOT_FOUND);
		Company company = optCompany.get();
		
		company.setRegisterState(dto.state());
		companyRepository.save(company);
	}
	
	public Optional<Company> findById(String companyId) {
		return companyRepository.findById(companyId);
	}
}
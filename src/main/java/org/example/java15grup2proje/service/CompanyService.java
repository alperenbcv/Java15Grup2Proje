package org.example.java15grup2proje.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.entity.enums.EState;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.CompanyRegisterRequestDto;
import org.example.java15grup2proje.entity.Company;
import org.example.java15grup2proje.repository.CompanyRepository;
import org.example.java15grup2proje.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
	private final CompanyRepository companyRepository;
	
	public void companyRegister(@Valid CompanyRegisterRequestDto dto){
		Company company = CompanyMapper.INSTANCE.fromCompanyRegisterRequestDto(dto);
		company.setAccountState(EState.PENDING);
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
		return companyRepository.getCompanyNames(EState.ACCEPTED);
	}
}
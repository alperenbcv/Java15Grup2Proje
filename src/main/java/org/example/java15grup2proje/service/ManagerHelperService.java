package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.repository.ManagerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerHelperService {
	private final ManagerRepository managerRepository;
	
	public String getCompanyIdByManagerId(String managerId){
		return managerRepository.findCompanyIdByManagerId(managerId);
	}
}
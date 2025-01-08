package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Company;
import org.example.java15grup2proje.entity.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
	
	Optional<Company> findCompanyByCompanyName (String companyName);
	
	@Query("SELECT c.companyName FROM Company c WHERE c.accountState=?1")
	List<String> getCompanyNames(EState state);
}
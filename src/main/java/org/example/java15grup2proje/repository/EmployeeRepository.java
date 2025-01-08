package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.dto.response.EmployeeListDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.model.EmployeeNameSurname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	Optional<Employee> findEmployeeByEmail (String email);
	
	List<Employee> findAllByCompanyId(String companyId);
	
	List<Employee> findAllByManagerId(String id);
	
	Optional<Employee> findByEmail(String email);
	
	@Query("SELECT new org.example.java15grup2proje.dto.response.EmployeeListDto(e.name, e.surname, e.email, e" +
			".department) FROM Employee as e WHERE e.companyId=?1 AND e.state=1 AND e.isAccountActive=true AND e" +
			".isAccountVerified=true " +
			"ORDER BY e.name ASC")
	List<EmployeeListDto> findEmployeeListDtoByCompany (String companyId);
	
	Optional<Employee> findEmployeeByActivationToken (String activationToken);
	
	List<Employee> findEmployeeByEmailIn (List<String> mailList);
	
	@Query("SELECT new org.example.java15grup2proje.model.EmployeeNameSurname(e.name,e.surname) FROM Employee as e WHERE e.id IN ?1")
	List<EmployeeNameSurname> findEmployeeNameSurnameByIdIn(List<String> id);
	
	List<Employee> findAllByCompanyIdAndState(String companyId, int state);
}
package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	Optional<Employee> findEmployeeByEmail (String email);
	
	List<Employee> findAllByCompanyId(String companyId);
	
	List<Employee> findAllByManagerId(String id);
	
	Optional<Employee> findByEmail(String email);
}
package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, String> {
	Optional<Manager> findManagerByEmail(String email);
	
	Optional<Manager> findManagerByActivationToken(String activationToken);
	
	@Query("SELECT m.companyId FROM Manager as m WHERE m.id=?1")
	String findCompanyIdByManagerId(String managerId);
	
	Boolean existsByEmail (String mail);
	
	boolean existsById (String id);
	
}
package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
	Optional<Manager> findManagerByEmail(String email);
}
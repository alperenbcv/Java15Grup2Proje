package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Possession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PossessionRepository extends JpaRepository<Possession, String> {
	
	List<Possession> findAllByPersonnelId(String id);
}
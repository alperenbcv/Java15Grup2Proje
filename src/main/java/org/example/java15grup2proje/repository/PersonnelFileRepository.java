package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.PersonnelFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelFileRepository extends JpaRepository<PersonnelFile, String> {
	List<PersonnelFile> findAllByPersonnelIdInAndState(List<String> personnelId, Integer state);
}
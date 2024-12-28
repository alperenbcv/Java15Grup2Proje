package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
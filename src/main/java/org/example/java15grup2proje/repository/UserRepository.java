package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
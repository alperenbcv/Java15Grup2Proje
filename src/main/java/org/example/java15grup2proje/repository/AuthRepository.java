package org.example.java15grup2proje.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
	Optional<Auth> findByEmail(@NotBlank @Email String email);
}
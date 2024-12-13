package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
	
	int deleteByUserId(Long userId);
}
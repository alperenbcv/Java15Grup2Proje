package org.example.java15grup2proje.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.entity.RefreshToken;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	
	@Value("${java15grup2proje.refreshtoken.duration}")
	private Long refreshTokenDurationMs;
	
	private final UserService userService;
	
	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken createRefreshToken(String userId){
		RefreshToken refreshToken = new RefreshToken();
		
		refreshToken.setUserId(userId);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		
		return refreshTokenRepository.save(refreshToken);
		
	}
	
	public RefreshToken verifyExpiration(RefreshToken token){
		if(token.getExpiryDate().compareTo(Instant.now()) < 0){
			refreshTokenRepository.delete(token);
			throw new Java15Grup2ProjeAppException(ErrorType.TOKEN_REFRESH_EXCEPTION);
		}
		return token;
	}
	
	@Transactional
	public int deleteByUserId(String userId){
		return refreshTokenRepository.deleteByUserId(userId);
	}
}
package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ManageStateRequestDto;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.entity.Possession;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.PossessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PossessionService {
	private final PossessionRepository possessionRepository;
	private final AuthService authService;
	
	public void managePossession(@Valid ManageStateRequestDto dto) {
		Auth auth = authService.tokenToAuth(dto.agentToken());
		if (auth.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		String possessionId = dto.itemId();
		Optional<Possession> optPossession = possessionRepository.findById(possessionId);
		if (optPossession.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.POSSESSION_NOT_FOUND);
		Possession possession = optPossession.get();
		if (!auth.getId().equals(possession.getManagerId())) throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		possession.setConfirmationState(dto.updatedState());
		possessionRepository.save(possession);
	}
	
	public List<Possession> getMyPossessions(String token) {
		Auth auth = authService.tokenToAuth(token);
		List<Possession> possessionList = possessionRepository.findAllByPersonnelId(auth.getId());
		return possessionList;
	}
}
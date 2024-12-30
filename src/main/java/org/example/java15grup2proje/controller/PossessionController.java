package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ManageStateRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.Possession;
import org.example.java15grup2proje.service.PossessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static  org.example.java15grup2proje.constant.RestApi.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(POSSESSION)
public class PossessionController {
	private final PossessionService possessionService;
	
	@PostMapping(MANAGE_POSSESSION)
	public ResponseEntity<BaseResponse<Boolean>> getPendingLeaves(@RequestBody @Valid ManageStateRequestDto dto){
		possessionService.managePossession(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .data(true)
		                                     .message("Possession managed")
		                                     .success(true).build());
		
	}
	
	@GetMapping(GET_MY_POSSESSIONS)
	public ResponseEntity<BaseResponse<List<Possession>>> getMyPossessions(String token){
		return ResponseEntity.ok(BaseResponse.<List<Possession>>builder()
		                                     .code(200)
		                                     .data(possessionService.getMyPossessions(token))
		                                     .message("Fetched the possessions of the employee")
		                                     .success(true).build());
		
	}
}
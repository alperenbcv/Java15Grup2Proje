package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.example.java15grup2proje.constant.RestApi.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(LEAVE)
public class LeaveController {
	private final LeaveService leaveService;
	@PostMapping(ADD_LEAVE)
	public ResponseEntity<BaseResponse<Boolean>> addLeave(@RequestBody @Valid LeaveRequestDto dto){
		leaveService.addLeave(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .data(true)
		                                     .message("Leave add successful.")
		                                     .success(true).build());
	
	}
}
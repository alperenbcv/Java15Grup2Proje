package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.dto.request.ManageLeaveDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.dto.response.LeaveResponseDto;
import org.example.java15grup2proje.entity.Leave;
import org.example.java15grup2proje.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(LEAVE)
@CrossOrigin("*")
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
	
	@GetMapping(GET_PENDING_LEAVES)
	public ResponseEntity<BaseResponse<List<LeaveResponseDto>>> getPendingLeaves(String token){
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponseDto>>builder()
		                                     .code(200)
		                                     .data(leaveService.getPendingLeaves(token))
		                                     .message("Leave add successful.")
		                                     .success(true).build());
		
	}
	
	@GetMapping(GET_LEAVES_BY_MANAGER)
	public ResponseEntity<BaseResponse<List<LeaveResponseDto>>> getLeavesByManager(String token){
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponseDto>>builder()
		                                     .code(200)
		                                     .data(leaveService.getLeavesByManager(token))
		                                     .message("Leave add successful.")
		                                     .success(true).build());
		
	}
	
	@PostMapping(MANAGE_LEAVE)
	public ResponseEntity<BaseResponse<Boolean>> getPendingLeaves(@RequestBody @Valid ManageLeaveDto dto){
		leaveService.manageLeave(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .data(true)
		                                     .message("Leave add successful.")
		                                     .success(true).build());
		
	}
	
}
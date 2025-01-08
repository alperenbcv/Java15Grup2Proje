package org.example.java15grup2proje.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ShiftDateRequestDto;
import org.example.java15grup2proje.dto.request.ShiftRequestDto;
import org.example.java15grup2proje.dto.request.ShiftUpdateRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.dto.response.ShiftResponseDto;
import org.example.java15grup2proje.service.ShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.example.java15grup2proje.constant.RestApi.*;
import static org.example.java15grup2proje.constant.RestApi.AUTH_SHIFT;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_SHIFT)
@CrossOrigin("*")
public class ShiftController {
	private final ShiftService shiftService;
	
	@PostMapping(CREATE_SHIFT)
	public ResponseEntity<BaseResponse<Boolean>> createShift(
			@RequestBody @Valid ShiftRequestDto shiftRequestDto,
			@RequestHeader("Authorization") String token) {
		String actualToken = token.replace("Bearer ", "");
		
		shiftService.createShift(shiftRequestDto, actualToken);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .success(true)
		                                     .data(true)
		                                     .message("Shift created successfully!")
		                                     .build());
	}
	
	
	@PostMapping(GET_SHIFT_FOR_MANAGER)
	public ResponseEntity<BaseResponse<List<ShiftResponseDto>>> getShiftByDateForManager(
			@RequestBody @Valid ShiftDateRequestDto shiftDateRequestDto,
			@RequestHeader("Authorization") String token) {
		
		String actualToken = token.replace("Bearer ", "");
		LocalDate shiftDate = shiftDateRequestDto.date();
		List<ShiftResponseDto> shiftResponse = shiftService.getShiftsByDateAndCompanyForManager(actualToken, shiftDate);
		
		return ResponseEntity.ok(BaseResponse.<List<ShiftResponseDto>>builder()
		                                     .code(200)
		                                     .success(true)
		                                     .data(shiftResponse)
		                                     .message("Shift brought successfully!")
		                                     .build());
	}
	
	@PostMapping(DELETE_SHIFT)
	public ResponseEntity<BaseResponse<Boolean>> deleteShift(@RequestHeader("Authorization") String token,
	                                                         @RequestBody Map<String, String> body){
		String actualToken = token.replace("Bearer ", "");
		String id = body.get("id");
		shiftService.deleteShift(actualToken,id);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).success(true).data(true).message("Shift " +
				                                                                                                    "deleted successfully!").build());
	}
	
	@PostMapping(UPDATE_SHIFT)
	public ResponseEntity<BaseResponse<Boolean>> updateShift(@RequestHeader("Authorization") String token,
	                                                         @RequestBody ShiftUpdateRequestDto shiftUpdateRequestDto){
		
		String actualToken = token.replace("Bearer ", "");
		shiftService.updateShift(shiftUpdateRequestDto,actualToken);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .success(true)
		                                     .data(true)
		                                     .message("Shift updated successfully!")
		                                     .build());
	}
	
	@PostMapping(GET_SHIFT_FOR_EMPLOYEE)
	public ResponseEntity<BaseResponse<List<ShiftResponseDto>>> getShiftByDateForEmployee(@RequestBody @Valid ShiftDateRequestDto shiftDateRequestDto,
	                                                                                      @RequestHeader("Authorization") String token){
		String actualToken = token.replace("Bearer ", "");
		LocalDate shiftDate = shiftDateRequestDto.date();
		return ResponseEntity.ok(BaseResponse.<List<ShiftResponseDto>>builder().code(200).success(true).message("Shifts brought successfully!")
		                                     .data(shiftService.getShiftsForEmployee(actualToken,shiftDate)).build());
	}
}
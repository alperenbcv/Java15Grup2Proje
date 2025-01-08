package org.example.java15grup2proje.dto.response;

import jakarta.validation.constraints.NotNull;
import org.example.java15grup2proje.entity.enums.EShiftType;

import java.time.LocalDateTime;
import java.util.List;

public record ShiftResponseDto(
		String shiftId,
		EShiftType shiftType,
		LocalDateTime startDate,
		LocalDateTime endDate,
		List<String> employeeNameSurname
		
) {
}
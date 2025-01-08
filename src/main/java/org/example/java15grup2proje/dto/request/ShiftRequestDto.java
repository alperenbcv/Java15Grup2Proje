package org.example.java15grup2proje.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.java15grup2proje.entity.enums.EShiftType;

import java.time.LocalDateTime;
import java.util.List;

public record ShiftRequestDto(
		@NotNull
		EShiftType shiftType,
		@NotNull
		LocalDateTime startDate,
		@NotNull
		LocalDateTime endDate,
		@NotNull
		List<String> emailList

) {
}
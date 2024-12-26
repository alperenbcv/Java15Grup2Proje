package org.example.java15grup2proje.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.java15grup2proje.entity.enums.ELeaveType;
import org.example.java15grup2proje.entity.enums.EState;

public record LeaveRequestDto(
	Long personnelId,
	Long startDate,
	Long endDate,
	ELeaveType leaveType,
	String description,
	Long appliedDate
	
	
) {

}
package org.example.java15grup2proje.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.enums.ELeaveType;

@Getter
@Setter
public class LeaveResponseDto {
	Long startDate;
	Long endDate;
	String description;
	ELeaveType leaveType;
	String leaveId;
}
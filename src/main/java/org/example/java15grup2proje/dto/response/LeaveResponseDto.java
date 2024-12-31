package org.example.java15grup2proje.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.enums.ELeaveType;
import org.example.java15grup2proje.entity.enums.EState;

@Getter
@Setter
public class LeaveResponseDto {
	Long startDate;
	Long endDate;
	EState state;
	String description;
	ELeaveType leaveType;
	String leaveId;
}
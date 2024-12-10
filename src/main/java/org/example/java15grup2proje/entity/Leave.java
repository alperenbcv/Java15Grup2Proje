package org.example.java15grup2proje.entity;

import org.example.java15grup2proje.entity.enums.ELeaveType;
import org.example.java15grup2proje.entity.enums.EState;

public class Leave {
	Long startDate;
	Long endDate;
	String description;
	Long personnelId;
	Long responseManagerId;
	ELeaveType leaveType;
	EState state;
	String rejectionReason;
	Long requestDate;
	Long responseDate;
}
package org.example.java15grup2proje.entity;

import org.example.java15grup2proje.entity.enums.EVacationType;

import java.util.List;

public class Vacation {
	private EVacationType vacationType;
	Long startDate;
	Long endDate;
	boolean isPublicHoliday;
	List<Long> affectedShifts;
	
}
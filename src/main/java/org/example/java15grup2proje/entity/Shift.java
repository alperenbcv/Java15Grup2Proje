package org.example.java15grup2proje.entity;

import jakarta.persistence.ManyToMany;
import org.example.java15grup2proje.entity.enums.EShiftType;

import java.util.List;

public class Shift {
	EShiftType shiftType;
	Long startDate;
	Long endDate;
	Long personnelId;
	boolean isOverTime;
}
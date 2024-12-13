package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.ELeaveType;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblleave")
public class Leave extends BaseEntity {
	Long startDate;
	Long endDate;
	String description;
	Long personnelId;
	Long responseManagerId;
	@Enumerated(EnumType.STRING)
	ELeaveType leaveType;
	@Enumerated(EnumType.STRING)
	EState leaveState;
	String rejectionReason;
	Long requestDate;
	Long responseDate;
}
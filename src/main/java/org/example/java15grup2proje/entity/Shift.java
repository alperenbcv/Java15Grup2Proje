package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EShiftType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblshift")
public class Shift extends BaseEntity{
	@Enumerated(EnumType.STRING)
	EShiftType shiftType;
	Long startDate;
	Long endDate;
	Long personnelId;
	boolean isOverTime;
}
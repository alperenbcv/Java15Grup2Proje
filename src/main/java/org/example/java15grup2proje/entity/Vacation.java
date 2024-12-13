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
import org.example.java15grup2proje.entity.enums.EVacationType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblvacation")
public class Vacation extends BaseEntity {
	@Enumerated(EnumType.STRING)
	EVacationType vacationType;
	Long startDate;
	Long endDate;
	boolean isPublicHoliday;
	
}
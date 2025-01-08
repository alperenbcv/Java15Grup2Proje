package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EVacationType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblvacation")
public class Vacation extends BaseEntity {
	@Enumerated(EnumType.STRING)
	EVacationType vacationType;
	Long startDate;
	Long endDate;
	boolean isPublicHoliday;
	
}
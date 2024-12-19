package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblassignment")
public class Assignment extends BaseEntity {
	Long assignmentDate;
	String assetName;
	String assetDescription;
	Long assignedToPersonnelId;
	Long assignedToManagerId;
	Long responseDate;
	@Enumerated(EnumType.STRING)
	EState status;
	String rejectionReason;
}
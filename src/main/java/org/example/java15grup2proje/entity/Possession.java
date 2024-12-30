package org.example.java15grup2proje.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblpossession")
public class Possession extends BaseEntity {
	String title;
	String description;
	String personnelId;
	String companyId;
	String managerId;
	Long lendingDate;
	Long returnDate;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	EState confirmationState = EState.PENDING;
}
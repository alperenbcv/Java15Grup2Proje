package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblexpense")
public class Expense extends BaseEntity {
	String personnelId;
	String managerId;
	@Builder.Default
	Long expenseDate = System.currentTimeMillis();
	String title;
	String description;
	Long cost;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	EState expenseState = EState.PENDING;
	String imageUrl;
	String fileUrl;
}
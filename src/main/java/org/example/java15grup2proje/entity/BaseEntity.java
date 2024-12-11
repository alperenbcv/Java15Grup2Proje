package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class BaseEntity {
	private Long createdAt;
	private Long updatedAt;
	private Long state;
}
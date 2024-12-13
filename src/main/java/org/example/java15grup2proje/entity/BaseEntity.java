package org.example.java15grup2proje.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Builder.Default
	private Long createdAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	@Builder.Default
	private Long updatedAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	@Builder.Default
	private Integer state = 1;
}
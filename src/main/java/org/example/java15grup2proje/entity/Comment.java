package org.example.java15grup2proje.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblcomment")
public class Comment extends BaseEntity{
	String managerId;
	String companyId;
	@Column(length = 1048575)
	String comment;
}
package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.ERole;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbladmin")
public class Admin extends BaseEntity{
	boolean isSuperAdmin;
	@Column(unique = true)
	String email;
	String password;
	@Enumerated(EnumType.STRING)
	ERole role;
}
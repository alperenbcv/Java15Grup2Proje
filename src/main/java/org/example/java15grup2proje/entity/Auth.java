package org.example.java15grup2proje.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.ERole;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblauth")
@Inheritance(strategy = InheritanceType.JOINED)
public class Auth extends BaseEntity{
	@Column(unique = true)
	String email;
	String password;
	@Enumerated(EnumType.STRING)
	ERole role;
	@Builder.Default
	boolean isAccountActive = false;
}
package org.example.java15grup2proje.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.entity.enums.ETitle;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class User extends BaseEntity {
	String name;
	String surname;
	@Column(unique = true)
	String email;
	String password;
	String phoneNumber;
	String pictureUrl;
	String address;
	@Enumerated(EnumType.STRING)
	EGender gender;
	@Builder.Default
	boolean isAccountVerified = false;
	@Builder.Default
	boolean isAccountActive = false;
	@Enumerated(EnumType.STRING)
	EDepartment department;
	String title;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private String companyId;
	@Enumerated(EnumType.STRING)
	ERole role;
}
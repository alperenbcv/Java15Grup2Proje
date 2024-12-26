package org.example.java15grup2proje.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ERole;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbluser")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
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
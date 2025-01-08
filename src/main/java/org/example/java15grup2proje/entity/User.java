package org.example.java15grup2proje.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbluser")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auth {
	String name;
	String surname;
	Long wage;
	String phoneNumber;
	String pictureUrl;
	String address;
	@Enumerated(EnumType.STRING)
	EGender gender;
	@Builder.Default
	boolean isAccountVerified = false;
	@Enumerated(EnumType.STRING)
	EDepartment department;
	String title;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private String companyId;
	@Column(unique = true)
	private String activationToken;
}
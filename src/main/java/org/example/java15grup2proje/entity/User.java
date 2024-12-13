package org.example.java15grup2proje.entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EGender;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class User extends BaseEntity {
	String name;
	String surname;
	String email;
	String password;
	String phoneNumber;
	String pictureUrl;
	String address;
	@Enumerated(EnumType.STRING)
	EGender gender;
	boolean isAccountVerified;
	
}
package org.example.java15grup2proje.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class User {
	String name;
	String surname;
	String email;
	String password;
	String phoneNumber;
	String pictureUrl;
	String address;
	boolean isAccountVerified;
	
}
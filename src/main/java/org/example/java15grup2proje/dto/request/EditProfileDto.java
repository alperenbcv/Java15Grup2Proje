package org.example.java15grup2proje.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ERole;

public record EditProfileDto (
	String token,
	//TODO unique check
	String email,
	String phoneNumber,
	String address,
	EGender gender
	//TODO birthdate gösterilsin ve değiştirilebilsin
	){
}
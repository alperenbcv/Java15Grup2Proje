package org.example.java15grup2proje.dto.response;

import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.entity.enums.EState;

public record EmployeeListDto(
		String name,
		String surname,
		String mail,
		EDepartment department
) {
}
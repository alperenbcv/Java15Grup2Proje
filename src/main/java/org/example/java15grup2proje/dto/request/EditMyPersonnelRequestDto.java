package org.example.java15grup2proje.dto.request;

import org.example.java15grup2proje.entity.Employee;

import java.util.List;

public record EditMyPersonnelRequestDto(
		String token,
		List<Employee> employees
) {
}
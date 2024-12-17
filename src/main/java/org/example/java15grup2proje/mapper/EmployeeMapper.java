package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
	@Mapping(target = "password", expression = "java(org.example.java15grup2proje.utility.PasswordHasher.passwordHash(dto.password()))")
	@Mapping(target = "birthDate", expression = "java(org.example.java15grup2proje.utility.TimeConverter" +
			".localDateToEpoch(dto.birthDate()))")
	Employee fromRegisterRequestDto(RegisterRequestDto dto);
}
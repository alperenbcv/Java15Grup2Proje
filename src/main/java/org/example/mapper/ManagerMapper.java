package org.example.mapper;

import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.security.NoSuchAlgorithmException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {
	ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);
	
	@Mapping(target = "password", expression = "java(org.example.java15grup2proje.utility.PasswordHasher.passwordHash(dto.password()))")
	@Mapping(target = "birthDate", expression = "java(org.example.java15grup2proje.utility.TimeConverter" +
			".localDateToEpoch(dto.birthDate()))")
	Manager fromRegisterRequestDto(RegisterRequestDto dto);
}
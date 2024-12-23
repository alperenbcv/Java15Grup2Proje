package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.utility.PassEncTech2;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.utility.PassEncTech2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.security.NoSuchAlgorithmException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = PassEncTech2.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target = "password", expression = "java(org.example.java15grup2proje.utility.PasswordHasher.passwordHash(dto.password()))")
	@Mapping(target = "birthDate", expression = "java(org.example.java15grup2proje.utility.TimeConverter" +
			".localDateToEpoch(dto.birthDate()))")
	User fromRegisterRequestDto(RegisterRequestDto dto) throws NoSuchAlgorithmException;
}
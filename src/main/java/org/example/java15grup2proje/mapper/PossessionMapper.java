package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.request.AddPossessionDto;
import org.example.java15grup2proje.entity.Possession;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PossessionMapper {
	public static PossessionMapper INSTANCE = Mappers.getMapper(PossessionMapper.class);
	
	Possession fromAddToPossession(AddPossessionDto dto, String personnelId, String companyId, String managerId);
}
package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeaveMapper {
	
	LeaveMapper INSTANCE = Mappers.getMapper(LeaveMapper.class);
	
	
	@Mapping(target = "leaveState", expression = "java(org.example.java15grup2proje.entity.enums.EState.PENDING)")
	
	Leave fromLeaveRequestDto(final LeaveRequestDto dto);
	
}
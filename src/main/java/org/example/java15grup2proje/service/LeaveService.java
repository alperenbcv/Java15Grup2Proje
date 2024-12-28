package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.entity.Leave;
import org.example.java15grup2proje.mapper.LeaveMapper;
import org.example.java15grup2proje.repository.LeaveRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveService {
	
	private final LeaveRepository leaveRepository;
	
	public void addLeave(LeaveRequestDto dto) {
		Leave leave = LeaveMapper.INSTANCE.fromLeaveRequestDto(dto);
		leaveRepository.save(leave);
	}
	
}
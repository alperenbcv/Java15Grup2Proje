package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Leave;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.entity.enums.EState;
import org.example.java15grup2proje.mapper.LeaveMapper;
import org.example.java15grup2proje.repository.LeaveRepository;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {
	private final JwtManager jwtManager;
	private final LeaveRepository leaveRepository;
	private final UserService userService;
	private final UserRepository userRepository;
	
	
	public void addLeave(LeaveRequestDto dto) {
		User user = userService.tokenToUser(dto.token());
		Employee employee = (Employee)user ;
		Leave leave = LeaveMapper.INSTANCE.fromLeaveRequestDto(dto, user.getId(), employee.getManagerId());
		leaveRepository.save(leave);
	}
	
	public List<Leave> getPendingLeaves(String token) {
		String managerId = userService.tokenToUserId(token);
		return leaveRepository.findAllByManagerIdAndLeaveState(managerId, EState.PENDING);
	}
}
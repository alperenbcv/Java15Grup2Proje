package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.LeaveRequestDto;
import org.example.java15grup2proje.dto.request.ManageLeaveDto;
import org.example.java15grup2proje.dto.response.LeaveResponseDto;
import org.example.java15grup2proje.dto.response.ProfileResponseDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Leave;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.entity.enums.EState;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.LeaveMapper;
import org.example.java15grup2proje.repository.LeaveRepository;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	
	public List<LeaveResponseDto> getPendingLeaves(String token) {
		String managerId = userService.tokenToUserId(token);
		List<Leave> pendingLeaves = leaveRepository.findAllByManagerIdAndLeaveState(managerId, EState.PENDING);
		LeaveMapper leaveMapper = LeaveMapper.INSTANCE;
		List<LeaveResponseDto> leaveResponseList =
				pendingLeaves.stream().map(leave -> leaveMapper.fromLeavetoResponse(leave)).toList();
		return leaveResponseList;
	}
	
	public void manageLeave(@Valid ManageLeaveDto dto) {
		User user = userService.tokenToUser(dto.token());
		if (user.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		String leaveId = dto.leaveId();
		Optional<Leave> optLeave = leaveRepository.findById(leaveId);
		if (optLeave.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.LEAVE_NOT_FOUND);
		Leave leave = optLeave.get();
		if (!user.getId().equals(leave.getManagerId())) throw new Java15Grup2ProjeAppException(ErrorType.NO_PERMISSION);
		leave.setLeaveState(dto.state());
		leaveRepository.save(leave);
	}
}
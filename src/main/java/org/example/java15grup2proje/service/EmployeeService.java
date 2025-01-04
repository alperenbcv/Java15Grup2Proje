package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.User;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.dto.request.RegisterRequestDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.repository.EmployeeRepository;
import org.example.java15grup2proje.repository.UserRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.example.java15grup2proje.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final JwtManager jwtManager;
	private final EmployeeRepository employeeRepository;
	private final ManagerService managerService;
	private final UserRepository userRepository;
	
	public void employeeRegister(@Valid RegisterRequestDto dto){
		Employee employee = EmployeeMapper.INSTANCE.fromRegisterRequestDto(dto);
		employee.setRole(ERole.EMPLOYEE);
		employeeRepository.save(employee);
	}
	
	public String employeeLogin(@Valid LoginRequestDto dto) {
		Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(dto.email());
		if (optionalEmployee.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), optionalEmployee.get().getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		String token = jwtManager.createToken(optionalEmployee.get().getId(),optionalEmployee.get().getRole().toString());
		return token;
	}
	
	public List<Employee> getPersonnelByCompanyId(String token) {
		Manager manager = managerService.tokenToManager(token);
		String companyId = manager.getCompanyId();
		return employeeRepository.findAllByCompanyId(companyId);
	}
	
	public Employee findById(String s) {
		return employeeRepository.findById(s).get();
	}
	
	public List<Employee> findAllByManagerId(String id) {
		return employeeRepository.findAllByManagerId(id);
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
}
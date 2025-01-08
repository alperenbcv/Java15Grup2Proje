package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.ShiftDateRequestDto;
import org.example.java15grup2proje.dto.request.ShiftRequestDto;
import org.example.java15grup2proje.dto.request.ShiftUpdateRequestDto;
import org.example.java15grup2proje.dto.response.ShiftResponseDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Shift;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.ShiftRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.TimeConverter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
	private final ShiftRepository shiftRepository;
	private final EmployeeService employeeService;
	private final JwtManager jwtManager;
	private final ManagerService managerService;
	private final MailService mailService;

	
	
	public void createShift(ShiftRequestDto shiftRequestDto,String token){
		Optional<String> optionalId = jwtManager.validateToken(token);
		if(optionalId.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		if(!managerService.managerExistsById(optionalId.get())){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_AUTHORIZED);
		}
		String companyId = managerService.getCompanyIdByManagerId(optionalId.get());
		Shift shift = Shift.builder().startDate(TimeConverter.localDateTimeToEpoch(shiftRequestDto.startDate()))
				.shiftType(shiftRequestDto.shiftType()).endDate(TimeConverter.localDateTimeToEpoch(shiftRequestDto.endDate()))
				.employeeList(employeeService.getEmployeeByMail(shiftRequestDto.emailList()))
				.shiftDate(TimeConverter.localDateToEpoch(shiftRequestDto.startDate().toLocalDate()))
				.companyId(companyId).build();
		for (String mail:shiftRequestDto.emailList()){
			mailService.sendShiftNotificationMail(mail);
		}
		shiftRepository.save(shift);
	}
	
	public List<ShiftResponseDto> getShiftsByDateAndCompanyForManager(String token, LocalDate date) {
		Optional<String> optionalId = jwtManager.validateToken(token);
		if (optionalId.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		if (!managerService.managerExistsById(optionalId.get())) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_AUTHORIZED);
		}
		
		String companyId = managerService.getCompanyIdByManagerId(optionalId.get());
		List<Shift> shifts = shiftRepository.findShiftsByCompanyIdAndShiftDate(companyId, TimeConverter.localDateToEpoch(date));
		
		if (shifts.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<ShiftResponseDto> responseDtos = new ArrayList<>();
		for (Shift shift : shifts) {
			List<Employee> employeeList = shift.getEmployeeList();
			List<String> empIdList = employeeList.stream()
			                                     .map(Employee::getId)
			                                     .toList();
			
			ShiftResponseDto shiftResponseDto = new ShiftResponseDto(
					shift.getId(),
					shift.getShiftType(),
					TimeConverter.epochToLocalDateTime(shift.getStartDate()),
					TimeConverter.epochToLocalDateTime(shift.getEndDate()),
					employeeService.getEmployeeNameSurnameById(empIdList)
			);
			
			responseDtos.add(shiftResponseDto);
		}
		
		return responseDtos;
	}
	
	public void updateShift(ShiftUpdateRequestDto shiftUpdateRequestDto, String token) {
		Optional<String> optionalId = jwtManager.validateToken(token);
		if (optionalId.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		if (!managerService.managerExistsById(optionalId.get())) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_AUTHORIZED);
		}
		
		Optional<Shift> byId = shiftRepository.findById(shiftUpdateRequestDto.shiftId());
		if (byId.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.SHIFT_NOT_FOUND);
		}
		
		Shift existingShift = byId.get();
		
		existingShift.setShiftType(shiftUpdateRequestDto.shiftType());
		existingShift.setStartDate(TimeConverter.localDateTimeToEpoch(shiftUpdateRequestDto.startDate()));
		existingShift.setEndDate(TimeConverter.localDateTimeToEpoch(shiftUpdateRequestDto.endDate()));
		
		existingShift.getEmployeeList().clear();
		existingShift.getEmployeeList().addAll(employeeService.getEmployeeByMail(shiftUpdateRequestDto.emailList()));
		
		shiftRepository.save(existingShift);
	}

	
	
	public void deleteShift(String token, String id){
		Optional<String> optionalId = jwtManager.validateToken(token);
		if(optionalId.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		if(!managerService.managerExistsById(optionalId.get())){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_AUTHORIZED);
		}
		Optional<Shift> byId = shiftRepository.findById(id);
		if(byId.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.SHIFT_NOT_FOUND);
		Shift shift = byId.get();
		shift.setState(0);
		shift.setIsDeleted(true);
		shiftRepository.save(shift);
	}
	
	public List<ShiftResponseDto> getShiftsForEmployee(String token, LocalDate shiftDate) {

		Optional<String> optionalId = jwtManager.validateToken(token);
		if (optionalId.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		
		if (!employeeService.existsById(optionalId.get())) {
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_AUTHORIZED);
		}

		String employeeId = optionalId.get();
		Employee employee = employeeService.findById(employeeId);
		
		List<Shift> shifts = shiftRepository.findShiftByEmployeeAndShiftDateAndIsDeletedIsFalse(employee,
		                                                                                         TimeConverter.localDateToEpoch(shiftDate));
		
		return shifts.stream()
		             .map(shift -> new ShiftResponseDto(
							 shift.getId(),
				             shift.getShiftType(),
				             TimeConverter.epochToLocalDateTime(shift.getStartDate()),
				             TimeConverter.epochToLocalDateTime(shift.getEndDate()),
							 shift.getEmployeeList().stream()
							      .map(emp -> emp.getName() + " " + emp.getSurname())
				                  .toList()))
		             .toList();
	}
	
}
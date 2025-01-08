package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.dto.response.ShiftResponseDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, String> {
	
	@Query("SELECT s FROM Shift s WHERE s.companyId = ?1 AND s.shiftDate = ?2 AND s.isDeleted = false")
	List<Shift> findShiftsByCompanyIdAndShiftDate(String companyId, Long startDate);
	
	Optional<Shift> findShiftByCompanyIdAndStartDateAndIsDeletedIsFalse(String companyId, Long startDate);
	
	@Query("SELECT s FROM Shift s JOIN s.employeeList e WHERE e = :emp AND s.isDeleted = false AND s.shiftDate = :shiftDate")
	List<Shift> findShiftByEmployeeAndShiftDateAndIsDeletedIsFalse(@Param("emp") Employee emp, @Param("shiftDate") Long shiftDate);
	
	
	
}
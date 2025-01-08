package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EShiftType;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblshift")
public class Shift extends BaseEntity{
	@Enumerated(EnumType.STRING)
	EShiftType shiftType;
	Long startDate;
	Long endDate;
	String companyId;
	Long shiftDate;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "employee_shift",
			joinColumns = @JoinColumn(name = "shift_id"),
			inverseJoinColumns = @JoinColumn(name = "employee_id")
	)
	List<Employee> employeeList;
	
}
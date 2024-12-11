package org.example.java15grup2proje.entity;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.ETitle;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblpersonnel")
public class Personnel extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private Long personnelNumber;
	@Enumerated(value = EnumType.STRING)
	private EDepartment department;
	@Enumerated(value = EnumType.STRING)
	private ETitle jobTitle;
	private Long hireDate;
	private Long birthDate;
	private boolean isOnLeave;
	private Long companyId;
	private Long managerId;
	
	
}
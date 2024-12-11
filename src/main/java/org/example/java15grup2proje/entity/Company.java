package org.example.java15grup2proje.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblcompany")
public class Company extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String companyName;
	private Long membershipPlanId;
}
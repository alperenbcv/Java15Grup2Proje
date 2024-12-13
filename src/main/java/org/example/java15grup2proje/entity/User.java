package org.example.java15grup2proje.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EState;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbluser")
public class User extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String surname;
	@Column(unique = true)
	String email;
	String password;
	String phoneNumber;
	String pictureUrl;
	String address;
	@Builder.Default
	boolean isAccountVerified = false;
	@Builder.Default
	boolean isAccountActive = false;
}
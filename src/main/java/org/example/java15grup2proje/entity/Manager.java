package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ETitle;

@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblmanager")
public class Manager extends User{
	
}
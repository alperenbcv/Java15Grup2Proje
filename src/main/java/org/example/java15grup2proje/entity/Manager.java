package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ETitle;

@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblmanager")
public class Manager extends User{
	
}
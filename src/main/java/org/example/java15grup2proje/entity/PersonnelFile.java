package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tblpersonnelfile")
public class PersonnelFile extends BaseEntity {
	Long personnelId;
	String fileType;
	String fileName;
	String fileUrl;
	Long uploadDate;
}
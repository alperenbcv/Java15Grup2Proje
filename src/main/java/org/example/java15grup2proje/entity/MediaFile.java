package org.example.java15grup2proje.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.java15grup2proje.entity.enums.EFileType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblmediafile")
public class MediaFile extends BaseEntity{
	String expenseId;
	String url;
	EFileType type;
}
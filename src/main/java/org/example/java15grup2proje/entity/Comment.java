package org.example.java15grup2proje.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblcomment")
public class Comment extends BaseEntity{
	Long managerId;
	String comment;
	Integer rating;
	private String companyMail;
	
}
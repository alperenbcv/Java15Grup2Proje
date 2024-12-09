package org.example.java15grup2proje.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Person {
	String name;
	
}
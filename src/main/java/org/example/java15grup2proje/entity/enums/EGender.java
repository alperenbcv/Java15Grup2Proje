package org.example.java15grup2proje.entity.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.java15grup2proje.utility.EGenderDeserializer;

@JsonDeserialize(using = EGenderDeserializer.class)
public enum EGender {
	WOMAN, MAN, OTHER
}
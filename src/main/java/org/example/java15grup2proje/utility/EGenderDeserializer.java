package org.example.java15grup2proje.utility;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.java15grup2proje.entity.enums.EGender;

import java.io.IOException;

public class EGenderDeserializer extends JsonDeserializer<EGender> {
	
	@Override
	public EGender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String value = p.getText();
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return EGender.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid gender value: " + value);
		}
	}
}
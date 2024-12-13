package org.example.java15grup2proje.utility;

import org.springframework.cglib.core.Local;

import java.time.*;

public class TimeConverter {
	
	public static Long LocalDateToEpoch (LocalDate date){
		return date.atTime(LocalTime.NOON).atZone(ZoneId.systemDefault()).toEpochSecond()*100;
	}
	
	public static Long LocalDateTimeToEpoch(LocalDateTime date){
		return date.atZone(ZoneId.systemDefault()).toEpochSecond()*100;
	}
	
	public static LocalDateTime EpochToLocalDateTime(Long epoch){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
		return localDateTime;
	}
	
	public static LocalDate epochToLocalDate(Long epoch){
		LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
		return localDate;
	}
}
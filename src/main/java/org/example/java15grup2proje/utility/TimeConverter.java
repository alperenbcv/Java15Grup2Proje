package org.example.java15grup2proje.utility;

import java.time.*;

public class TimeConverter {
	
	public static Long localDateToEpoch(LocalDate date){
		return date.atTime(LocalTime.NOON).atZone(ZoneId.systemDefault()).toEpochSecond()*100;
	}
	
	public static Long localDateTimeToEpoch(LocalDateTime date){
		return date.atZone(ZoneId.systemDefault()).toEpochSecond()*100;
	}
	
	public static LocalDateTime epochToLocalDateTime(Long epoch){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
		return localDateTime;
	}
	
	public static LocalDate epochToLocalDate(Long epoch){
		LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
		return localDate;
	}
}
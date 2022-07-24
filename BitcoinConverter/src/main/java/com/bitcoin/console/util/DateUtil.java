package com.bitcoin.console.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static String getCurrentDateString(){
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(formatter);
	}
	
	public static String getPreviousDateString(int interval)
	{
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.minusDays(interval).format(formatter);
	}

}

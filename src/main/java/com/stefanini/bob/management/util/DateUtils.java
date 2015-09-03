package com.stefanini.bob.management.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateUtils {

	public static int getDifferenceBetweenTwoDates(Date date1, Date date2){
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date1);
		
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		
		DateTime dateTime1 = new DateTime(calendar1.get(Calendar.YEAR), 
										  calendar1.get(Calendar.MONTH), 
										  calendar1.get(Calendar.DAY_OF_MONTH), 
										  calendar1.get(Calendar.HOUR_OF_DAY), 
										  calendar1.get(Calendar.MINUTE), 
										  calendar1.get(Calendar.SECOND), 
										  calendar1.get(Calendar.MILLISECOND));

		DateTime dateTime2 = new DateTime(
				  calendar2.get(Calendar.YEAR), 
				  calendar2.get(Calendar.MONTH), 
				  calendar2.get(Calendar.DAY_OF_MONTH), 
				  calendar2.get(Calendar.HOUR_OF_DAY), 
				  calendar2.get(Calendar.MINUTE), 
				  calendar2.get(Calendar.SECOND), 
				  calendar2.get(Calendar.MILLISECOND));
		
		return Days.daysBetween(dateTime1, dateTime2).getDays();
	}
}

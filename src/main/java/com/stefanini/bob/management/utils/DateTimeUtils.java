package com.stefanini.bob.management.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Classe utilit�ria para opera��es de data e hora
 * @author fpsouza
 */
public class DateTimeUtils {

	
	/**
	 * M�todo para acrescentar ou decrescer unidades de uma determinada data retornando a data resultante do c�lculo
	 * @param date - data alvo da opera��o
	 * @param field - inteiro que representa o campo a ser acrescido ou descrescido (Usar o Calendar.DAY_OF_MONTH, por exemplo)
	 * @param amount - quantidade a ser acrescida (passar valor +) ou descrescida (passar valor -)
	 * @return Data acrescida/descrescida da quantidade e unidades passadas como param�tro
	 */
	public static Date add(Date date, int field, int amount){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
	}
	
	public static boolean isMonday(Date date){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}
	
	
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

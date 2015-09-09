package com.stefanini.bob.management.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}

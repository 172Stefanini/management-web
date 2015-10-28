package com.stefanini.bob.management.services.impl;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.services.TimeSheetService;

public class TimeSheetServiceImpl implements TimeSheetService {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public List<TimeSheet> findAllTimesheetWithRound(String sortFieldName,
			String sortOrder, List<Person> persons, Date from, Date to,
			Person of) {
		
		List<TimeSheet> dataBaseList = TimeSheet.findAllTimeSheets("workHours", "ASC", persons, from, to, of);
		Hashtable<Person, List<TimeSheet>> hashTableByPerson = new Hashtable<Person, List<TimeSheet>>();
		List<TimeSheet> resultList = new ArrayList<TimeSheet>();
		
		for (TimeSheet timeSheet : dataBaseList) {
			
			this.entityManager.detach(timeSheet);
			
			if(hashTableByPerson.containsKey(timeSheet.getPerson()))
				hashTableByPerson.get(timeSheet.getPerson()).add(timeSheet);
			else{
				hashTableByPerson.put(timeSheet.getPerson(), new ArrayList<TimeSheet>());
				hashTableByPerson.get(timeSheet.getPerson()).add(timeSheet);
			}
		}
		
		
		for (Person keyPerson : hashTableByPerson.keySet()) {

			List<TimeSheet> listTimeSheetByPerson = hashTableByPerson.get(keyPerson);
			
			Set<Integer> setOfDecimals = new HashSet<Integer>();
			
			for (TimeSheet timeSheet : listTimeSheetByPerson) {
				BigDecimal roudedValue = timeSheet.getWorkHours().setScale(0, RoundingMode.UP);
				BigDecimal differenceOfValueVersusRounded = timeSheet.getWorkHours().subtract(roudedValue, new MathContext(1, RoundingMode.UP));
				//coloca o sinal como positivo (menos com menos dá mais)
				differenceOfValueVersusRounded = differenceOfValueVersusRounded.negate();
				
				if(!differenceOfValueVersusRounded.equals(new BigDecimal(0,new MathContext(1)).setScale(1))){
					if(setOfDecimals.contains(new BigDecimal(1, new MathContext(1)).subtract(differenceOfValueVersusRounded).multiply(new BigDecimal(10)).intValue())){
						timeSheet.setWorkHours(timeSheet.getWorkHours().subtract(new BigDecimal(1, new MathContext(1)).subtract(differenceOfValueVersusRounded)));
						setOfDecimals.remove(new BigDecimal(1, new MathContext(1)).subtract(differenceOfValueVersusRounded).multiply(new BigDecimal(10)).intValue());
					}else{
						timeSheet.setWorkHours(roudedValue);
						setOfDecimals.add(differenceOfValueVersusRounded.multiply(new BigDecimal(10)).intValue());
					}
				}
				resultList.add(timeSheet);
			}
		}
		
		return resultList;
	}
}

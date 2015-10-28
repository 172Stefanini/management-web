package com.stefanini.bob.management.services;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.TimeSheet;

@RooService(domainTypes = { com.stefanini.bob.management.domain.TimeSheet.class })
public interface TimeSheetService {
	
	public List<TimeSheet> findAllTimesheetWithRound(String sortFieldName, String sortOrder, List<Person> persons, Date from, Date to, Person of);
}

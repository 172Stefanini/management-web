package com.stefanini.bob.management.web;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TimesheetFilter {
	
	public TimesheetFilter(){
		
	}
	
	public TimesheetFilter(Date filterDataFrom, Date filterDataTo, Long personId){
		this.filterDataFrom = filterDataFrom;
		this.filterDataTo = filterDataTo;
		this.personId = personId;
	}

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date filterDataFrom;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date filterDataTo;
	
	private Long personId;

	public Date getFilterDataFrom() {
		return filterDataFrom;
	}

	public void setFilterDataFrom(Date filterDataFrom) {
		this.filterDataFrom = filterDataFrom;
	}

	public Date getFilterDataTo() {
		return filterDataTo;
	}

	public void setFilterDataTo(Date filterDataTo) {
		this.filterDataTo = filterDataTo;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}

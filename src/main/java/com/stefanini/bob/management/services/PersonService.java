package com.stefanini.bob.management.services;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Person;

@RooService(domainTypes = { com.stefanini.bob.management.domain.Person.class })
public interface PersonService {
	
	public Person getPersonByAccessUserName(String accessUserName);
	
	public List<Person> findByManager(Person person);
}

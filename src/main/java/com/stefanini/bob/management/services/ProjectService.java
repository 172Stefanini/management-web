package com.stefanini.bob.management.services;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.Project;

@RooService(domainTypes = { com.stefanini.bob.management.domain.Project.class })
public interface ProjectService {
	
	public List<Project> findByPerson(Person person);
}

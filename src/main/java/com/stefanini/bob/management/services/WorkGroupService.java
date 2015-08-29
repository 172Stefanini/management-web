package com.stefanini.bob.management.services;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.WorkGroup;

@RooService(domainTypes = { com.stefanini.bob.management.domain.WorkGroup.class })
public interface WorkGroupService {
	
	public List<WorkGroup> findByPerson(Person person);
}

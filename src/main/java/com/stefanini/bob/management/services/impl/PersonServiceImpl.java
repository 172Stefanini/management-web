package com.stefanini.bob.management.services.impl;
import org.springframework.beans.factory.annotation.Autowired;

import com.stefanini.bob.management.dao.PersonDAO;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.services.PersonService;

public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Override
	public Person getPersonByAccessUserName(String accessUserName) {
		return personDAO.findByAccessUserName(accessUserName);
	}
}

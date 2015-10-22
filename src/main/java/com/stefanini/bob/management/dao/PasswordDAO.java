package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.Password;
import com.stefanini.bob.management.domain.Person;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Password.class)
public interface PasswordDAO {
	
	public Password findOneByPerson(Person person);
}

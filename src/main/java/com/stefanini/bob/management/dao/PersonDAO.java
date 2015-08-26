package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.Person;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Person.class)
public interface PersonDAO {
	
	public Person findByAccessUserName(String accessUserName);
}

package com.stefanini.bob.management.dao;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.stefanini.bob.management.domain.PersonProjectRelationship;

@RooJpaRepository(domainType = PersonProjectRelationship.class)
public interface PersonProjectRelationshipDAO {
}

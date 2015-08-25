package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.PersonProjectRelationship;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = PersonProjectRelationship.class)
public interface PersonProjectRelationshipDAO {
}

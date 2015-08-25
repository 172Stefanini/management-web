package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.PersonWorkGroupRelationship;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = PersonWorkGroupRelationship.class)
public interface PersonWorkGroupRelationshipDAO {
}

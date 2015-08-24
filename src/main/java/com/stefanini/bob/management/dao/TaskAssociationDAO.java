package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.TaskAssociation;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TaskAssociation.class)
public interface TaskAssociationDAO {
}

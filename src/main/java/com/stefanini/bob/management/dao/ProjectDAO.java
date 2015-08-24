package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.Project;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Project.class)
public interface ProjectDAO {
}

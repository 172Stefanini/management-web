package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.WorkGroup;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = WorkGroup.class)
public interface WorkGroupDAO {
}

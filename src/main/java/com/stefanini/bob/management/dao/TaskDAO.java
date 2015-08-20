package com.stefanini.bob.management.dao;
import com.stefanini.bob.management.domain.Task;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Task.class)
public interface TaskDAO {
}

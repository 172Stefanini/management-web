package com.stefanini.bob.management.dao;
import java.util.List;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Project;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Category.class)
public interface CategoryDAO {

	public List<Category> findByProject(Project project);
}

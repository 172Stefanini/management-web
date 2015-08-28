package com.stefanini.bob.management.services;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Project;

@RooService(domainTypes = { com.stefanini.bob.management.domain.Category.class })
public interface CategoryService {
	
	public List<Category> findByProject(Project project);
	
}

package com.stefanini.bob.management.services;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Task;

@RooService(domainTypes = { com.stefanini.bob.management.domain.Task.class })
public interface TaskService {

	public List<Task> findByCategory(Category category);
}

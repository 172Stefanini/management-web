package com.stefanini.bob.management.services.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Task;
import com.stefanini.bob.management.services.TaskService;

public class TaskServiceImpl implements TaskService {
	

	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<Task> findByCategory(Category category){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Task> criteriaTask = builder.createQuery(Task.class);
		Root<Task> root = criteriaTask.from(Task.class);
		criteriaTask.select(root);
		
		Join<Task, Category> joinTaskCategory = root.join("category");
		
		criteriaTask.where(builder.equal(joinTaskCategory.get("id"), category.getId()));
		
		List<Task> listTask = entityManager.createQuery(criteriaTask).getResultList();
		
		return listTask;
	}
}

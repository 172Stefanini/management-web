package com.stefanini.bob.management.services.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<Category> findByProject(Project project){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Category> criteriaCategory = builder.createQuery(Category.class);
		Root<Category> root = criteriaCategory.from(Category.class);
		criteriaCategory.select(root);
		
		Join<Category, Project> joinCategoryProject = root.join("project");
		
		criteriaCategory.where(builder.equal(joinCategoryProject.get("id"), project.getId()));
		
		List<Category> listCategory = entityManager.createQuery(criteriaCategory).getResultList();
		
		return listCategory;
	}
}

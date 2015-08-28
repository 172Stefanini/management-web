package com.stefanini.bob.management.services.impl;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.PersonProjectRelationship;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.services.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<Project> findByPerson(Person person){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<PersonProjectRelationship> criteriaProject = builder.createQuery(PersonProjectRelationship.class);
		Root<PersonProjectRelationship> root = criteriaProject.from(PersonProjectRelationship.class);
		criteriaProject.select(root);
		
		Join<PersonProjectRelationship, Person> joinRelPerson = root.join("person");
		
		criteriaProject.where(builder.equal(joinRelPerson.get("id"), person.getId()));
		
		List<PersonProjectRelationship> listRel = entityManager.createQuery(criteriaProject).getResultList();
		
		List<Project> returnList = new LinkedList<Project>();
		
		for (PersonProjectRelationship relationship : listRel) {
			returnList.add(relationship.getProject());
		}
		
		return returnList;
	}
}

package com.stefanini.bob.management.services.impl;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.stefanini.bob.management.dao.PersonDAO;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.PersonProjectRelationship;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.ProjectService;

public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private ProjectService projectService;

	@Override
	public Person getPersonByAccessUserName(String accessUserName) {
		return personDAO.findByAccessUserName(accessUserName);
	}
	
	public List<Person> findByManager(Person person){
		
		List<Project> listOfProjects = projectService.findByPerson(person);
		List<Long> ids =  new LinkedList<Long>();
		for (Project project : listOfProjects) {
			ids.add(project.getId());
		}
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<PersonProjectRelationship> criteria = builder.createQuery(PersonProjectRelationship.class);
		Root<PersonProjectRelationship> root = criteria.from(PersonProjectRelationship.class);
		criteria.select(root);
		
		Join<PersonProjectRelationship, Project> joinPersonProject = root.join("project");
		
		criteria.where(joinPersonProject.get("id").in(ids));
		
		List<PersonProjectRelationship> listProjects = entityManager.createQuery(criteria).getResultList();
		
		List<Person> listPersonsWithSameProjectsOfManager = new LinkedList<Person>();
		for (PersonProjectRelationship personProjectRelationship : listProjects) {
			if(!listPersonsWithSameProjectsOfManager.contains(personProjectRelationship.getPerson()))
				listPersonsWithSameProjectsOfManager.add(personProjectRelationship.getPerson());
		}
		
		return listPersonsWithSameProjectsOfManager;
		
	}
}

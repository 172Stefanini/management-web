package com.stefanini.bob.management.services.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.PersonWorkGroupRelationship;
import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.WorkGroupService;

public class WorkGroupServiceImpl implements WorkGroupService {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<WorkGroup> findByPerson(Person person){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<PersonWorkGroupRelationship> criteriaWorkGroup = builder.createQuery(PersonWorkGroupRelationship.class);
		Root<PersonWorkGroupRelationship> root = criteriaWorkGroup.from(PersonWorkGroupRelationship.class);
		criteriaWorkGroup.select(root);
		
		Join<PersonWorkGroupRelationship, Person> joinRelPerson = root.join("person");
		Join<PersonWorkGroupRelationship, WorkGroup> joinRelWorkGroup = root.join("workGroup");
		
		criteriaWorkGroup.where(
					builder.equal(joinRelPerson.get("id"), person.getId()),
					builder.equal(joinRelWorkGroup.get("id"), root.get("workGroup").get("id")));
		
		List<PersonWorkGroupRelationship> listRelWorkGroup = entityManager.createQuery(criteriaWorkGroup).getResultList();
		
		List<WorkGroup> listWorkGroup = new ArrayList<WorkGroup>(); 
		
		for (PersonWorkGroupRelationship personWorkGroupRelationship : listRelWorkGroup) {
			listWorkGroup.add(personWorkGroupRelationship.getWorkGroup());
		}
		
		return listWorkGroup;
	}
}

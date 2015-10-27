package com.stefanini.bob.management.domain;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.stefanini.bob.management.utils.DateTimeUtils;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Person {

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    /**
     */
    @NotNull
    @Size(max = 200)
    private String name;

    /**
     */
    @Column(unique = true)
    private String accessUserName;

	@SuppressWarnings("unchecked")
	public static List<Person> findAllPendingPeople(String sortFieldName, String sortOrder, WorkGroup workGroup) {
		Query query = buildQuery(sortFieldName, sortOrder, workGroup, true);
		return query.getResultList();
    }

	@SuppressWarnings("unchecked")
	public static List<Person> findPendingPersonEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder, WorkGroup workGroup) {
		Query query = buildQuery(sortFieldName, sortOrder, workGroup, true);
        return query.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	private static Query buildQuery(String sortFieldName, String sortOrder, WorkGroup workGroup, boolean isNotOnlyForCount) {
		String jpaQuery = "select p.* from person p ";

		if(workGroup!=null){
			jpaQuery += " inner join person_work_group_relationship pwr on pwr.person = p.id";
		}

		jpaQuery += " where not exists (select * from time_sheet t where t.person = p.id and (t.occurrence_date = :yesterday))";

		if(workGroup!=null){
			jpaQuery += " and pwr.work_group = :wg";
		}

		if(isNotOnlyForCount){
		    if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
	            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
	            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
	                jpaQuery = jpaQuery + " " + sortOrder;
	            }
	        }
		}
	    
        Query query = entityManager().createNativeQuery(jpaQuery, Person.class)
        		.setParameter("yesterday", DateTimeUtils.add(new Date(), Calendar.DAY_OF_MONTH, DateTimeUtils.isMonday(new Date())?-3:-1), TemporalType.DATE);

        if(workGroup!=null){
        	query.setParameter("wg", workGroup);
        }
		return query;
	}
	
	public static Integer countAllPendingPeople(WorkGroup workGroup) {
       Query query = buildQuery(null, null, workGroup, false);
       return query.getResultList().size();
    }
}

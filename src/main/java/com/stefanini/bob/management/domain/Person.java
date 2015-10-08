package com.stefanini.bob.management.domain;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

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

	public static List<Person> findAllPendingPeople(String sortFieldName, String sortOrder) {
		String jpaQuery = "SELECT p FROM Person p";
        jpaQuery += " where 16 > (";
        jpaQuery += "    select sum(t.workHours) from TimeSheet t where t.person = p.id and (occurrenceDate = :now or occurrenceDate = :yesterday)";
        jpaQuery += " ) or not exists (";
        jpaQuery += "    select t from TimeSheet t where t.person = p.id and (occurrenceDate = :now or occurrenceDate = :yesterday)";
        jpaQuery += " )";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Person.class)
        		.setParameter("now", new Date(), TemporalType.DATE)
        		.setParameter("yesterday", DateUtils.addDays(new Date(), -1), TemporalType.DATE)
        		.getResultList();
    }

	public static List<Person> findPendingPersonEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
		String jpaQuery = "SELECT p FROM Person p";
        jpaQuery += " where 16 > (";
        jpaQuery += "    select sum(t.workHours) from TimeSheet t where t.person = p.id and (occurrenceDate = :now or occurrenceDate = :yesterday)";
        jpaQuery += " ) or not exists (";
        jpaQuery += "    select t from TimeSheet t where t.person = p.id and (occurrenceDate = :now or occurrenceDate = :yesterday)";
        jpaQuery += " )";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Person.class)
        		.setParameter("now", new Date(), TemporalType.DATE)
        		.setParameter("yesterday", DateUtils.addDays(new Date(), -1), TemporalType.DATE)
        		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}

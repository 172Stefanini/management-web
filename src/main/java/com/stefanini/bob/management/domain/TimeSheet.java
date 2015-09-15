package com.stefanini.bob.management.domain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TimeSheet {

    /**
     */
    @OneToOne
    private Category category;

    /**
     */
    @OneToOne
    private WorkGroup workGroup;

    /**
     */
    @OneToOne
    private Person person;

    /**
     */
    private Boolean overtime;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date occurrenceDate;

    /**
     */
    @OneToOne
    private Task task;

    /**
     */
    @Size(max = 4000)
    private String note;

    /**
     */
    @DecimalMin("0")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal workHours;

    public String getStringfyOvertime(){
    	return overtime?"Não":"Sim";
    }
    
    /**
     */
    @ManyToOne
    private Project project;

	public static List<TimeSheet> findTimeSheetEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder, List<Person> persons, Date from, Date to, Person of) {
		if(persons.isEmpty()) return new LinkedList<TimeSheet>();
		
		
        String jpaQuery = "SELECT o FROM TimeSheet o WHERE o.person in (:listPersons)";
        
        if(from!=null && to !=null)
        	jpaQuery+= " AND o.occurrenceDate BETWEEN :from AND :to";
        
        if(of!=null)
        	jpaQuery+= " AND o.person = :of";
        
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }

        TypedQuery<TimeSheet> query = entityManager().createQuery(jpaQuery, TimeSheet.class);
        query.setParameter("listPersons", persons);
        if(from!=null && to !=null){
	    	query.setParameter("from", from);
	    	query.setParameter("to", to);
        }
        if(of!=null)
        	query.setParameter("of", of);
        
        return query.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<TimeSheet> findAllTimeSheets(String sortFieldName, String sortOrder, List<Person> persons, Date from, Date to, Person of) {
		if(persons.isEmpty()) return new LinkedList<TimeSheet>();
		
        String jpaQuery = "SELECT o FROM TimeSheet o  WHERE o.person in (:listPersons)";

        if(from!=null && to !=null)
        	jpaQuery+= " AND o.occurrenceDate BETWEEN :from AND :to";
        
        if(of!=null)
        	jpaQuery+= " AND o.person = :of";

        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        
        TypedQuery<TimeSheet> query = entityManager().createQuery(jpaQuery, TimeSheet.class);
        query.setParameter("listPersons", persons);
        if(from!=null && to !=null){
	    	query.setParameter("from", from);
	    	query.setParameter("to", to);
        }
        if(of!=null)
        	query.setParameter("of", of);
        
        return query.getResultList();
    }
}

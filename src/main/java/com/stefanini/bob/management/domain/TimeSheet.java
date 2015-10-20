package com.stefanini.bob.management.domain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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

    public String getStringfyOvertime() {
        return overtime ? "Sim" : "Não";
    }

    /**
     */
    @ManyToOne
    private Project project;

    @Transient
    private Boolean deleteAllowed = true;

    @Transient
    private Boolean updateAllowed = true;

    public String getDeleteButton() {
        return "<form id='command' action='/management/timesheets/" + this.getId() + "' method='post' style='display:" + (this.getDeleteAllowed() ? "inline" : "none") + "'>" + "<input type='hidden' name='_method' value='DELETE'>" + "<input onclick=\"return confirm('Você tem certeza que quer remover este item?');\" value='Remover Registro de Horas' " + "type='image' title='Remover Registro de Horas' src='/management/resources/images/delete.png' class='image' alt='Remover Registro de Horas'>" + "<input value='1' type='hidden' name='page'><input value='10' type='hidden' name='size'>" + "</form>";
    }

    public String getEditButton() {
        return "<a title='Atualizar Registro de Horas' alt='Atualizar Registro de Horas' href='/management/timesheets/" + this.getId() + "?form' style='display:" + (this.getUpdateAllowed() ? "inline" : "none") + "'>" + "<img title='Atualizar Registro de Horas' src='/management/resources/images/update.png' class='image' alt='Atualizar Registro de Horas'>" + "</a>";
    }

    public static List<TimeSheet> findTimeSheetEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder, List<Person> persons, Date from, Date to, Person of) {
        if (persons.isEmpty()) return new LinkedList<TimeSheet>();
        String jpaQuery = "SELECT o FROM TimeSheet o WHERE o.person in (:listPersons)";
        if (from != null && to != null) jpaQuery += " AND o.occurrenceDate BETWEEN :from AND :to";
        if (of != null) jpaQuery += " AND o.person = :of";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }else{
        	jpaQuery = jpaQuery + " ORDER BY " + "o.occurrenceDate DESC";
        }
        TypedQuery<TimeSheet> query = entityManager().createQuery(jpaQuery, TimeSheet.class);
        query.setParameter("listPersons", persons);
        if (from != null && to != null) {
            query.setParameter("from", from, TemporalType.DATE);
            query.setParameter("to", to, TemporalType.DATE);
        }
        if (of != null) query.setParameter("of", of);
        return query.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<TimeSheet> findAllTimeSheets(String sortFieldName, String sortOrder, List<Person> persons, Date from, Date to, Person of) {
        if (persons.isEmpty()) return new LinkedList<TimeSheet>();
        String jpaQuery = "SELECT o FROM TimeSheet o  WHERE o.person in (:listPersons)";
        if (from != null && to != null) jpaQuery += " AND o.occurrenceDate BETWEEN :from AND :to";
        if (of != null) jpaQuery += " AND o.person = :of";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }else{
        	jpaQuery = jpaQuery + " ORDER BY " + "o.occurrenceDate DESC";
        }
        TypedQuery<TimeSheet> query = entityManager().createQuery(jpaQuery, TimeSheet.class);
        query.setParameter("listPersons", persons);
        if (from != null && to != null) {
            query.setParameter("from", from, TemporalType.DATE);
            query.setParameter("to", to, TemporalType.DATE);
        }
        if (of != null) query.setParameter("of", of);
        return query.getResultList();
    }

    public Boolean getDeleteAllowed() {
        return deleteAllowed;
    }

    public void setDeleteAllowed(Boolean deleteAllowed) {
        this.deleteAllowed = deleteAllowed;
    }

    public Boolean getUpdateAllowed() {
        return updateAllowed;
    }

    public void setUpdateAllowed(Boolean updateAllowed) {
        this.updateAllowed = updateAllowed;
    }

    /**
     */
    private Boolean taskComplete;

    /**
     */
    @Digits(integer = 2, fraction = 1)
    private BigDecimal estimateFinish;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("category", "workGroup", "person.name", "overtime", "occurrenceDate", "task", "note", "workHours", "project.name", "deleteAllowed", "updateAllowed", "taskComplete", "estimateFinish");
}

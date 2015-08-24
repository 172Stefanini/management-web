package com.stefanini.bob.management.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

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
    @Enumerated
    private TeamEnum team;

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
    @DateTimeFormat(style = "M-")
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
}

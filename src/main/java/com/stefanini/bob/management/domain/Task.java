package com.stefanini.bob.management.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Task {

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
    private String description;
}

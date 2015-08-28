package com.stefanini.bob.management.domain;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Task {

    /**
     */
    private String description;

    /**
     */
    private Boolean rework;

    /**
     */
    @ManyToOne
    private Category category;
}

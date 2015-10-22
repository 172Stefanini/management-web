package com.stefanini.bob.management.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Password {

    /**
     */
    @NotNull
    private String keyPass;

    /**
     */
    @OneToOne
    private Person person;

    /**
     */
    @Transient
    private String oldPassword;
}

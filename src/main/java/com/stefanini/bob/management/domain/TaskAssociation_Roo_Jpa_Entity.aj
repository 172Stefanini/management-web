// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.domain;

import com.stefanini.bob.management.domain.TaskAssociation;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect TaskAssociation_Roo_Jpa_Entity {
    
    declare @type: TaskAssociation: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long TaskAssociation.id;
    
    @Version
    @Column(name = "version")
    private Integer TaskAssociation.version;
    
    public Long TaskAssociation.getId() {
        return this.id;
    }
    
    public void TaskAssociation.setId(Long id) {
        this.id = id;
    }
    
    public Integer TaskAssociation.getVersion() {
        return this.version;
    }
    
    public void TaskAssociation.setVersion(Integer version) {
        this.version = version;
    }
    
}

// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.services.impl;

import com.stefanini.bob.management.dao.TaskAssociationDAO;
import com.stefanini.bob.management.domain.TaskAssociation;
import com.stefanini.bob.management.services.impl.TaskAssociationServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TaskAssociationServiceImpl_Roo_Service {
    
    declare @type: TaskAssociationServiceImpl: @Service;
    
    declare @type: TaskAssociationServiceImpl: @Transactional;
    
    @Autowired
    TaskAssociationDAO TaskAssociationServiceImpl.taskAssociationDAO;
    
    public long TaskAssociationServiceImpl.countAllTaskAssociations() {
        return taskAssociationDAO.count();
    }
    
    public void TaskAssociationServiceImpl.deleteTaskAssociation(TaskAssociation taskAssociation) {
        taskAssociationDAO.delete(taskAssociation);
    }
    
    public TaskAssociation TaskAssociationServiceImpl.findTaskAssociation(Long id) {
        return taskAssociationDAO.findOne(id);
    }
    
    public List<TaskAssociation> TaskAssociationServiceImpl.findAllTaskAssociations() {
        return taskAssociationDAO.findAll();
    }
    
    public List<TaskAssociation> TaskAssociationServiceImpl.findTaskAssociationEntries(int firstResult, int maxResults) {
        return taskAssociationDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void TaskAssociationServiceImpl.saveTaskAssociation(TaskAssociation taskAssociation) {
        taskAssociationDAO.save(taskAssociation);
    }
    
    public TaskAssociation TaskAssociationServiceImpl.updateTaskAssociation(TaskAssociation taskAssociation) {
        return taskAssociationDAO.save(taskAssociation);
    }
    
}

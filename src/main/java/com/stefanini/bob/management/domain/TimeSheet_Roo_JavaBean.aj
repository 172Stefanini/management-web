// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.domain;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.domain.Task;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.domain.WorkGroup;
import java.math.BigDecimal;
import java.util.Date;

privileged aspect TimeSheet_Roo_JavaBean {
    
    public Category TimeSheet.getCategory() {
        return this.category;
    }
    
    public void TimeSheet.setCategory(Category category) {
        this.category = category;
    }
    
    public WorkGroup TimeSheet.getWorkGroup() {
        return this.workGroup;
    }
    
    public void TimeSheet.setWorkGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }
    
    public Person TimeSheet.getPerson() {
        return this.person;
    }
    
    public void TimeSheet.setPerson(Person person) {
        this.person = person;
    }
    
    public Boolean TimeSheet.getOvertime() {
        return this.overtime;
    }
    
    public void TimeSheet.setOvertime(Boolean overtime) {
        this.overtime = overtime;
    }
    
    public Date TimeSheet.getOccurrenceDate() {
        return this.occurrenceDate;
    }
    
    public void TimeSheet.setOccurrenceDate(Date occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }
    
    public Task TimeSheet.getTask() {
        return this.task;
    }
    
    public void TimeSheet.setTask(Task task) {
        this.task = task;
    }
    
    public String TimeSheet.getNote() {
        return this.note;
    }
    
    public void TimeSheet.setNote(String note) {
        this.note = note;
    }
    
    public BigDecimal TimeSheet.getWorkHours() {
        return this.workHours;
    }
    
    public void TimeSheet.setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }
    
    public Project TimeSheet.getProject() {
        return this.project;
    }
    
    public void TimeSheet.setProject(Project project) {
        this.project = project;
    }
    
    public Boolean TimeSheet.getTaskComplete() {
        return this.taskComplete;
    }
    
    public void TimeSheet.setTaskComplete(Boolean taskComplete) {
        this.taskComplete = taskComplete;
    }
    
    public BigDecimal TimeSheet.getEstimateFinish() {
        return this.estimateFinish;
    }
    
    public void TimeSheet.setEstimateFinish(BigDecimal estimateFinish) {
        this.estimateFinish = estimateFinish;
    }
    
}

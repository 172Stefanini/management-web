// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.web;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.PersonProjectRelationship;
import com.stefanini.bob.management.domain.PersonWorkGroupRelationship;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.domain.Task;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonProjectRelationshipService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.PersonWorkGroupRelationshipService;
import com.stefanini.bob.management.services.ProjectService;
import com.stefanini.bob.management.services.TaskService;
import com.stefanini.bob.management.services.TimeSheetService;
import com.stefanini.bob.management.services.WorkGroupService;
import com.stefanini.bob.management.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    CategoryService ApplicationConversionServiceFactoryBean.categoryService;
    
    @Autowired
    PersonService ApplicationConversionServiceFactoryBean.personService;
    
    @Autowired
    PersonProjectRelationshipService ApplicationConversionServiceFactoryBean.personProjectRelationshipService;
    
    @Autowired
    PersonWorkGroupRelationshipService ApplicationConversionServiceFactoryBean.personWorkGroupRelationshipService;
    
    @Autowired
    ProjectService ApplicationConversionServiceFactoryBean.projectService;
    
    @Autowired
    TaskService ApplicationConversionServiceFactoryBean.taskService;
    
    @Autowired
    TimeSheetService ApplicationConversionServiceFactoryBean.timeSheetService;
    
    @Autowired
    WorkGroupService ApplicationConversionServiceFactoryBean.workGroupService;
    
    public Converter<Category, String> ApplicationConversionServiceFactoryBean.getCategoryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.Category, java.lang.String>() {
            public String convert(Category category) {
                return new StringBuilder().append(category.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, Category> ApplicationConversionServiceFactoryBean.getIdToCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.Category>() {
            public com.stefanini.bob.management.domain.Category convert(java.lang.Long id) {
                return categoryService.findCategory(id);
            }
        };
    }
    
    public Converter<String, Category> ApplicationConversionServiceFactoryBean.getStringToCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.Category>() {
            public com.stefanini.bob.management.domain.Category convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Category.class);
            }
        };
    }
    
    public Converter<Person, String> ApplicationConversionServiceFactoryBean.getPersonToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.Person, java.lang.String>() {
            public String convert(Person person) {
                return new StringBuilder().append(person.getName()).append(' ').append(person.getAccessUserName()).toString();
            }
        };
    }
    
    public Converter<Long, Person> ApplicationConversionServiceFactoryBean.getIdToPersonConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.Person>() {
            public com.stefanini.bob.management.domain.Person convert(java.lang.Long id) {
                return personService.findPerson(id);
            }
        };
    }
    
    public Converter<String, Person> ApplicationConversionServiceFactoryBean.getStringToPersonConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.Person>() {
            public com.stefanini.bob.management.domain.Person convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Person.class);
            }
        };
    }
    
    public Converter<PersonProjectRelationship, String> ApplicationConversionServiceFactoryBean.getPersonProjectRelationshipToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.PersonProjectRelationship, java.lang.String>() {
            public String convert(PersonProjectRelationship personProjectRelationship) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, PersonProjectRelationship> ApplicationConversionServiceFactoryBean.getIdToPersonProjectRelationshipConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.PersonProjectRelationship>() {
            public com.stefanini.bob.management.domain.PersonProjectRelationship convert(java.lang.Long id) {
                return personProjectRelationshipService.findPersonProjectRelationship(id);
            }
        };
    }
    
    public Converter<String, PersonProjectRelationship> ApplicationConversionServiceFactoryBean.getStringToPersonProjectRelationshipConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.PersonProjectRelationship>() {
            public com.stefanini.bob.management.domain.PersonProjectRelationship convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PersonProjectRelationship.class);
            }
        };
    }
    
    public Converter<PersonWorkGroupRelationship, String> ApplicationConversionServiceFactoryBean.getPersonWorkGroupRelationshipToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.PersonWorkGroupRelationship, java.lang.String>() {
            public String convert(PersonWorkGroupRelationship personWorkGroupRelationship) {
                return "(no displayable fields)";
            }
        };
    }
    
    public Converter<Long, PersonWorkGroupRelationship> ApplicationConversionServiceFactoryBean.getIdToPersonWorkGroupRelationshipConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.PersonWorkGroupRelationship>() {
            public com.stefanini.bob.management.domain.PersonWorkGroupRelationship convert(java.lang.Long id) {
                return personWorkGroupRelationshipService.findPersonWorkGroupRelationship(id);
            }
        };
    }
    
    public Converter<String, PersonWorkGroupRelationship> ApplicationConversionServiceFactoryBean.getStringToPersonWorkGroupRelationshipConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.PersonWorkGroupRelationship>() {
            public com.stefanini.bob.management.domain.PersonWorkGroupRelationship convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PersonWorkGroupRelationship.class);
            }
        };
    }
    
    public Converter<Project, String> ApplicationConversionServiceFactoryBean.getProjectToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.Project, java.lang.String>() {
            public String convert(Project project) {
                return new StringBuilder().append(project.getName()).toString();
            }
        };
    }
    
    public Converter<Long, Project> ApplicationConversionServiceFactoryBean.getIdToProjectConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.Project>() {
            public com.stefanini.bob.management.domain.Project convert(java.lang.Long id) {
                return projectService.findProject(id);
            }
        };
    }
    
    public Converter<String, Project> ApplicationConversionServiceFactoryBean.getStringToProjectConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.Project>() {
            public com.stefanini.bob.management.domain.Project convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Project.class);
            }
        };
    }
    
    public Converter<Task, String> ApplicationConversionServiceFactoryBean.getTaskToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.Task, java.lang.String>() {
            public String convert(Task task) {
                return new StringBuilder().append(task.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, Task> ApplicationConversionServiceFactoryBean.getIdToTaskConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.Task>() {
            public com.stefanini.bob.management.domain.Task convert(java.lang.Long id) {
                return taskService.findTask(id);
            }
        };
    }
    
    public Converter<String, Task> ApplicationConversionServiceFactoryBean.getStringToTaskConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.Task>() {
            public com.stefanini.bob.management.domain.Task convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Task.class);
            }
        };
    }
    
    public Converter<TimeSheet, String> ApplicationConversionServiceFactoryBean.getTimeSheetToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.TimeSheet, java.lang.String>() {
            public String convert(TimeSheet timeSheet) {
                return new StringBuilder().append(timeSheet.getOccurrenceDate()).append(' ').append(timeSheet.getNote()).append(' ').append(timeSheet.getWorkHours()).toString();
            }
        };
    }
    
    public Converter<Long, TimeSheet> ApplicationConversionServiceFactoryBean.getIdToTimeSheetConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.TimeSheet>() {
            public com.stefanini.bob.management.domain.TimeSheet convert(java.lang.Long id) {
                return timeSheetService.findTimeSheet(id);
            }
        };
    }
    
    public Converter<String, TimeSheet> ApplicationConversionServiceFactoryBean.getStringToTimeSheetConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.TimeSheet>() {
            public com.stefanini.bob.management.domain.TimeSheet convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), TimeSheet.class);
            }
        };
    }
    
    public Converter<WorkGroup, String> ApplicationConversionServiceFactoryBean.getWorkGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.stefanini.bob.management.domain.WorkGroup, java.lang.String>() {
            public String convert(WorkGroup workGroup) {
                return new StringBuilder().append(workGroup.getName()).toString();
            }
        };
    }
    
    public Converter<Long, WorkGroup> ApplicationConversionServiceFactoryBean.getIdToWorkGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.stefanini.bob.management.domain.WorkGroup>() {
            public com.stefanini.bob.management.domain.WorkGroup convert(java.lang.Long id) {
                return workGroupService.findWorkGroup(id);
            }
        };
    }
    
    public Converter<String, WorkGroup> ApplicationConversionServiceFactoryBean.getStringToWorkGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.stefanini.bob.management.domain.WorkGroup>() {
            public com.stefanini.bob.management.domain.WorkGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WorkGroup.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getCategoryToStringConverter());
        registry.addConverter(getIdToCategoryConverter());
        registry.addConverter(getStringToCategoryConverter());
        registry.addConverter(getPersonToStringConverter());
        registry.addConverter(getIdToPersonConverter());
        registry.addConverter(getStringToPersonConverter());
        registry.addConverter(getPersonProjectRelationshipToStringConverter());
        registry.addConverter(getIdToPersonProjectRelationshipConverter());
        registry.addConverter(getStringToPersonProjectRelationshipConverter());
        registry.addConverter(getPersonWorkGroupRelationshipToStringConverter());
        registry.addConverter(getIdToPersonWorkGroupRelationshipConverter());
        registry.addConverter(getStringToPersonWorkGroupRelationshipConverter());
        registry.addConverter(getProjectToStringConverter());
        registry.addConverter(getIdToProjectConverter());
        registry.addConverter(getStringToProjectConverter());
        registry.addConverter(getTaskToStringConverter());
        registry.addConverter(getIdToTaskConverter());
        registry.addConverter(getStringToTaskConverter());
        registry.addConverter(getTimeSheetToStringConverter());
        registry.addConverter(getIdToTimeSheetConverter());
        registry.addConverter(getStringToTimeSheetConverter());
        registry.addConverter(getWorkGroupToStringConverter());
        registry.addConverter(getIdToWorkGroupConverter());
        registry.addConverter(getStringToWorkGroupConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}

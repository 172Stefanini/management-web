package com.stefanini.bob.management.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.ProjectService;
import com.stefanini.bob.management.services.TaskService;
import com.stefanini.bob.management.services.WorkGroupService;

@RequestMapping("/timesheets")
@Controller
@RooWebScaffold(path = "timesheets", formBackingObject = TimeSheet.class)
public class TimeSheetController {
	
	@Autowired
    private CategoryService categoryService;

	@Autowired
    private PersonService personService;

	@Autowired
    private ProjectService projectService;

	@Autowired
    private TaskService taskService;

	@Autowired
    private WorkGroupService workGroupService;
	
	private SecurityContextUtils securityContextUtils;

    void populateEditForm(Model uiModel, TimeSheet timeSheet) {
    	securityContextUtils = new SecurityContextUtils();
    	
    	List<Person> listPeopleToShow = new ArrayList<Person>(); 
    	List<Project> listProjectsToShow = new ArrayList<Project>();
    	
    	if(securityContextUtils.hasRole("ROLE_ADMIN")){
    		listPeopleToShow.addAll(personService.findAllPeople());
    		listProjectsToShow.addAll(projectService.findAllProjects());
    	}else{
    		Person loggedPerson = personService.getPersonByAccessUserName(securityContextUtils.getLoggedUserName());
        	listPeopleToShow.add(loggedPerson);
        	listProjectsToShow.addAll(projectService.findByPerson(loggedPerson));
    	}
    	
        uiModel.addAttribute("timeSheet", timeSheet);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("categorys", categoryService.findAllCategorys());
        uiModel.addAttribute("people", listPeopleToShow);
        uiModel.addAttribute("projects", listProjectsToShow);
        uiModel.addAttribute("tasks", taskService.findAllTasks());
        uiModel.addAttribute("workgroups", workGroupService.findAllWorkGroups());
    }
    
}

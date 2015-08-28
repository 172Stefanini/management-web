package com.stefanini.bob.management.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.domain.Task;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.ProjectService;
import com.stefanini.bob.management.services.TaskService;
import com.stefanini.bob.management.services.WorkGroupService;

import flexjson.JSONSerializer;

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
    	List<Category> listCategoriesToShow = new ArrayList<Category>();
    	List<Task> listTasksToShow = new ArrayList<Task>();
    	
    	if(securityContextUtils.hasRole("ROLE_ADMIN")){
    		listPeopleToShow.addAll(personService.findAllPeople());
    	}else{
    		Person loggedPerson = personService.getPersonByAccessUserName(securityContextUtils.getLoggedUserName());
        	listPeopleToShow.add(loggedPerson);
    	}

		if(!listPeopleToShow.isEmpty())
			listProjectsToShow.addAll(projectService.findByPerson(listPeopleToShow.get(0)));
		
		if(!listProjectsToShow.isEmpty())
			listCategoriesToShow.addAll(categoryService.findByProject(listProjectsToShow.get(0)));
		
		if(!listCategoriesToShow.isEmpty())
			listTasksToShow.addAll(taskService.findByCategory(listCategoriesToShow.get(0)));

    	
        uiModel.addAttribute("timeSheet", timeSheet);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("categorys", listCategoriesToShow);
        uiModel.addAttribute("people", listPeopleToShow);
        uiModel.addAttribute("projects", listProjectsToShow);
        uiModel.addAttribute("tasks", listTasksToShow);
        uiModel.addAttribute("workgroups", workGroupService.findAllWorkGroups());
    }
    
    
	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new TimeSheet());
        return "timesheets/create";
    }
	
	@RequestMapping(params = "updateProjectCombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateProjectCombo(
    		@RequestParam(value = "personId", required = false) Long personId,
    		Model uiModel) {
        
		List<Project> projects = projectService.findByPerson(personService.findPerson(personId));
		
        return new JSONSerializer().serialize(projects);
    }

	@RequestMapping(params = "updateCategoryCombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateCategoryCombo(
    		@RequestParam(value = "projectId", required = false) Long projectId,
    		Model uiModel) {
        		
		List<Category> categories = categoryService.findByProject(projectService.findProject(projectId));
		
        return new JSONSerializer().serialize(categories);
    }
	
	@RequestMapping(params = "updateTaskCombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateTaskCombo(
    		@RequestParam(value = "categoryId", required = false) Long categoryId, 
    		Model uiModel) {
        		
		List<Task> tasks = taskService.findByCategory(categoryService.findCategory(categoryId));
		
        return new JSONSerializer().serialize(tasks);
    }

}

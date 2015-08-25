package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.ProjectService;
import com.stefanini.bob.management.services.TaskService;
import com.stefanini.bob.management.services.WorkGroupService;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timesheets")
@Controller
@RooWebScaffold(path = "timesheets", formBackingObject = TimeSheet.class)
public class TimeSheetController {
	
	private CategoryService categoryService;
	
	private PersonService personService;
	
	private ProjectService projectService;
	
	private TaskService taskService;
	
	private WorkGroupService workGroupService;

	void populateEditForm(Model uiModel, TimeSheet timeSheet) {
        uiModel.addAttribute("timeSheet", timeSheet);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("categorys", categoryService.findAllCategorys());
        uiModel.addAttribute("people", personService.findAllPeople());
        uiModel.addAttribute("projects", projectService.findAllProjects());
        uiModel.addAttribute("tasks", taskService.findAllTasks());
        uiModel.addAttribute("workgroups", workGroupService.findAllWorkGroups());
    }
}

package com.stefanini.bob.management.web;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.Project;
import com.stefanini.bob.management.domain.Task;
import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.ProjectService;
import com.stefanini.bob.management.services.TaskService;
import com.stefanini.bob.management.services.WorkGroupService;
import com.stefanini.bob.management.utils.DateTimeUtils;

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
	
	private List<TimeSheet> listTimesheet;
	
	private SecurityContextUtils securityContextUtils;

    void populateEditForm(Model uiModel, TimeSheet timeSheet) {
    	
    	List<Person> listPeopleToShow = getListOfPeopleByPermission();

    	List<Project> listProjectsToShow = new ArrayList<Project>();
    	List<Category> listCategoriesToShow = new ArrayList<Category>();
    	List<Task> listTasksToShow = new ArrayList<Task>();
    	List<WorkGroup> listWorkGroupToShow = new ArrayList<WorkGroup>();
    	
		if(!listPeopleToShow.isEmpty())
			listProjectsToShow.addAll(projectService.findByPerson(listPeopleToShow.get(timeSheet.getPerson()==null?0:listPeopleToShow.indexOf(timeSheet.getPerson()))));
		
		if(!listProjectsToShow.isEmpty())
			listCategoriesToShow.addAll(categoryService.findByProject(listProjectsToShow.get(timeSheet.getProject()==null?0:listProjectsToShow.indexOf(timeSheet.getProject()))));
		
		if(!listCategoriesToShow.isEmpty())
			listTasksToShow.addAll(taskService.findByCategory(listCategoriesToShow.get(timeSheet.getCategory()==null?0:listCategoriesToShow.indexOf(timeSheet.getCategory()))));

		if(!listPeopleToShow.isEmpty())
			listWorkGroupToShow.addAll(workGroupService.findByPerson(listPeopleToShow.get(timeSheet.getPerson()==null?0:listPeopleToShow.indexOf(timeSheet.getPerson()))));
		
		if(timeSheet.getOccurrenceDate()==null)
			timeSheet.setOccurrenceDate(new Date());
		
    	
        uiModel.addAttribute("timeSheet", timeSheet);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("categorys", listCategoriesToShow);
        uiModel.addAttribute("people", listPeopleToShow);
        uiModel.addAttribute("projects", listProjectsToShow);
        uiModel.addAttribute("tasks", listTasksToShow);
        uiModel.addAttribute("workgroups", listWorkGroupToShow);
        
        Integer daysPast = new Integer(1);
        Date yesterday = DateTimeUtils.add(new Date(), Calendar.DAY_OF_MONTH, -1);
        if(DateTimeUtils.isMonday(yesterday));
        	daysPast = 3;
        
        uiModel.addAttribute("daysPast", daysPast);
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
	
	@RequestMapping(params = "updateWorkGroupCombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateWorkGroupCombo(
    		@RequestParam(value = "personId", required = false) Long personId,
    		Model uiModel) {
        
		List<WorkGroup> workGroups = workGroupService.findByPerson(personService.findPerson(personId));
		
        return new JSONSerializer().serialize(workGroups);
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
	
	@RequestMapping(params = "find")
    public String find(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel,
    		@RequestParam(value = "filterDataFrom", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date filterDataFrom, 
    		@RequestParam(value = "filterDataTo", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date filterDataTo,
    		@RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder,
    		@RequestParam(value = "personId", required = false) Long personId) {
		List<Person> listPeopleToFilter = getListOfPeopleByPermission();

		Person personToPutFirst = null;
		for (Person person : listPeopleToFilter) {
			if(person.getId().longValue() == personId.longValue())
				personToPutFirst = person;
		}
		
		
		find(page, size, sortFieldName, sortOrder, uiModel, listPeopleToFilter, filterDataFrom, filterDataTo, personToPutFirst);
		
		
		Person allPerson = new Person();
		allPerson.setId(0L);
		allPerson.setName("Todos");
		listPeopleToFilter.add(0, allPerson);
		
		if(personToPutFirst!=null){
			listPeopleToFilter.remove(personToPutFirst);
			listPeopleToFilter.add(0, personToPutFirst);
		}
		
		uiModel.addAttribute("filter", new TimesheetFilter(filterDataFrom, filterDataTo, personId));
		
		uiModel.addAttribute("people", listPeopleToFilter);
        addDateTimeFormatPatterns(uiModel);
		return "timesheets/list";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		List<Person> listPeopleToFilter = getListOfPeopleByPermission();
		Date filterDataFrom = DateTimeUtils.add(new Date(), Calendar.DAY_OF_MONTH, -30);
		Date filterDataTo = new Date();
		
		find(page, size, sortFieldName, sortOrder, uiModel, listPeopleToFilter, filterDataFrom, filterDataTo, null);		
		
		Person allPerson = new Person();
		allPerson.setId(0L);
		allPerson.setName("Todos");
		listPeopleToFilter.add(0, allPerson);
		uiModel.addAttribute("people", listPeopleToFilter);

		uiModel.addAttribute("filter", new TimesheetFilter(filterDataFrom, filterDataTo, null));
        addDateTimeFormatPatterns(uiModel);
        return "timesheets/list";
    }


	private void find(Integer page, Integer size, String sortFieldName,
			String sortOrder, Model uiModel, List<Person> listPeopleToFilter,
			Date filterDataFrom, Date filterDataTo, Person of) {
		if (page != null || size != null) {
            int sizeNo = size == null ? 100 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            this.listTimesheet = TimeSheet.findTimeSheetEntries(firstResult, sizeNo, sortFieldName, sortOrder, listPeopleToFilter, filterDataFrom, filterDataTo, of);
            uiModel.addAttribute("timesheets", this.listTimesheet);
            float nrOfPages = (float) timeSheetService.countAllTimeSheets() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
        	this.listTimesheet = TimeSheet.findAllTimeSheets(sortFieldName, sortOrder, listPeopleToFilter, filterDataFrom, filterDataTo, of);
            uiModel.addAttribute("timesheets", this.listTimesheet);
        }
		
		for (TimeSheet timeSheet : this.listTimesheet) {
			securityContextUtils = new SecurityContextUtils();
			if(!(securityContextUtils.hasRole("ROLE_ADMIN") || securityContextUtils.hasRole("ROLE_MANAGER")) && 
					DateTimeUtils.getDifferenceBetweenTwoDates(timeSheet.getOccurrenceDate(), new Date()) > 1){
				timeSheet.setUpdateAllowed(false);
				timeSheet.setDeleteAllowed(false);
			}
		}
		
		uiModel.addAttribute("timesheets", this.listTimesheet);
	}
	
	@RequestMapping(params = "excel")
    public ModelAndView excelExport(Model uiModel) {
		return new ModelAndView("TimesheetDailyExcelView", "timesheetList", this.listTimesheet);

    }
	
	private List<Person> getListOfPeopleByPermission(){
		List<Person> listPeopleToFilter = new ArrayList<Person>();
		securityContextUtils = new SecurityContextUtils();
    	Person loggedPerson = personService.getPersonByAccessUserName(securityContextUtils.getLoggedUserName());

    	
		if(securityContextUtils.hasRole("ROLE_ADMIN")){
    		listPeopleToFilter.addAll(personService.findAllPeople());
    	}else if(securityContextUtils.hasRole("ROLE_MANAGER")){
    		listPeopleToFilter.addAll(personService.findByManager(loggedPerson));
    	}else{
        	listPeopleToFilter.add(loggedPerson);
    	}
		
		listPeopleToFilter.remove(loggedPerson);
		listPeopleToFilter.add(0, loggedPerson);
		
		return listPeopleToFilter;
	}
}

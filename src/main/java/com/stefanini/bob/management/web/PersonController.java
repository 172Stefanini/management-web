package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.WorkGroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/people")
@Controller
@RooWebScaffold(path = "people", formBackingObject = Person.class)
public class PersonController {
	
	@Autowired
	private WorkGroupService workGroupService;
	
	@RequestMapping(value = "/pendings", produces = "text/html")
    public String pendings(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        
		processFindPendings(page, size, sortFieldName, sortOrder, null, uiModel);

        WorkGroup workGroup = new WorkGroup();
        workGroup.setId(0L);
        workGroup.setName("Todos");
        
        List<WorkGroup> listOfAllWG = workGroupService.findAllWorkGroups();
        
        listOfAllWG.add(0, workGroup);
        
        uiModel.addAttribute("workgroups", listOfAllWG);
        uiModel.addAttribute("filter", new PendingPersonFilter(null));
        
        return "people/listPendings";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        
		if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("people", Person.findPersonEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) personService.countAllPeople() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("people", Person.findAllPeople(sortFieldName, sortOrder));
        }
        return "people/list";
    }
	
	@RequestMapping(params = "find")
    public String findPendings(@RequestParam(value = "page", required = false) Integer page, 
    				   @RequestParam(value = "size", required = false) Integer size, 
    				   @RequestParam(value = "sortFieldName", required = false) String sortFieldName, 
    				   @RequestParam(value = "sortOrder", required = false) String sortOrder, 
    				   @RequestParam(value = "workGroupId", required = false) Long workGroupId,
    				   Model uiModel) {
		
		WorkGroup allGroups = new WorkGroup();
        allGroups.setId(0L);
        allGroups.setName("Todos");
        
        List<WorkGroup> listOfAllWG = workGroupService.findAllWorkGroups();
        
        listOfAllWG.add(0, allGroups);
        
        
        WorkGroup workGroupToPutFirst = null;
        
        for (WorkGroup workGroup : listOfAllWG) {
			if(workGroup.getId() == workGroupId){
				workGroupToPutFirst = workGroup; 
			}
		}
        
        if(workGroupToPutFirst!=null){
        	listOfAllWG.remove(workGroupToPutFirst);
        	listOfAllWG.add(0, workGroupToPutFirst);
        }
		
        uiModel.addAttribute("filter", new PendingPersonFilter(workGroupId));
        uiModel.addAttribute("workgroups", listOfAllWG);
        
		processFindPendings(page, size, sortFieldName, sortOrder, workGroupId, uiModel);
		
		return "people/listPendings";
	}
		
		
		
	 
	
	private void processFindPendings(Integer page, Integer size, String sortFieldName, String sortOrder, Long workGroupId, Model uiModel){
		
		WorkGroup workGroup = null;
		
		if(workGroupId != null)
			workGroup = workGroupService.findWorkGroup(workGroupId);
		
		if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("people", Person.findPendingPersonEntries(firstResult, sizeNo, sortFieldName, sortOrder, workGroup));
            float nrOfPages = (float) Person.countAllPendingPeople(workGroup) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("people", Person.findAllPendingPeople(sortFieldName, sortOrder, workGroup));
        }
	}
	
}

package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.Category;
import com.stefanini.bob.management.domain.Person;
import com.stefanini.bob.management.domain.TaskAssociation;
import com.stefanini.bob.management.domain.TeamEnum;
import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.CategoryService;
import com.stefanini.bob.management.services.PersonService;
import com.stefanini.bob.management.services.WorkGroupService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/taskassociations")
@Controller
@RooWebScaffold(path = "taskassociations", formBackingObject = TaskAssociation.class)
public class TaskAssociationController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PersonService personService;

    @Autowired
    private WorkGroupService workGroupService;

    void populateEditForm(Model uiModel, TaskAssociation taskAssociation) {
        List<Category> categories = categoryService.findAllCategorys();
        categories.add(0, new Category("Selecione"));
        List<Person> people = personService.findAllPeople();
        people.add(0, new Person("Selecione"));
        List<WorkGroup> workGroups = workGroupService.findAllWorkGroups();
        workGroups.add(0, new WorkGroup("Selecione"));
        uiModel.addAttribute("taskAssociation", taskAssociation);
        uiModel.addAttribute("categorys", categories);
        uiModel.addAttribute("people", people);
        uiModel.addAttribute("teamenums", Arrays.asList(TeamEnum.values()));
        uiModel.addAttribute("workgroups", workGroups);
    }
}

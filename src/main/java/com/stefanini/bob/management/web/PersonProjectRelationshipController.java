package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.PersonProjectRelationship;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personprojectrelationships")
@Controller
@RooWebScaffold(path = "personprojectrelationships", formBackingObject = PersonProjectRelationship.class)
public class PersonProjectRelationshipController {
}

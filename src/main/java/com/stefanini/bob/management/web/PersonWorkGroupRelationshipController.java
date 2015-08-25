package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.PersonWorkGroupRelationship;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personworkgrouprelationships")
@Controller
@RooWebScaffold(path = "personworkgrouprelationships", formBackingObject = PersonWorkGroupRelationship.class)
public class PersonWorkGroupRelationshipController {
}

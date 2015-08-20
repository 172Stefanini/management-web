package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.WorkGroup;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/workgroups")
@Controller
@RooWebScaffold(path = "workgroups", formBackingObject = WorkGroup.class)
public class WorkGroupController {
}

package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.TimeSheet;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timesheets")
@Controller
@RooWebScaffold(path = "timesheets", formBackingObject = TimeSheet.class)
public class TimeSheetController {
}

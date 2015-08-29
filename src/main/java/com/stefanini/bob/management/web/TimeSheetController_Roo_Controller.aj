// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.web;

import com.stefanini.bob.management.domain.TimeSheet;
import com.stefanini.bob.management.services.TimeSheetService;
import com.stefanini.bob.management.web.TimeSheetController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TimeSheetController_Roo_Controller {
    
    @Autowired
    TimeSheetService TimeSheetController.timeSheetService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TimeSheetController.create(@Valid TimeSheet timeSheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeSheet);
            return "timesheets/create";
        }
        uiModel.asMap().clear();
        timeSheetService.saveTimeSheet(timeSheet);
        return "redirect:/timesheets/" + encodeUrlPathSegment(timeSheet.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TimeSheetController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("timesheet", timeSheetService.findTimeSheet(id));
        uiModel.addAttribute("itemId", id);
        return "timesheets/show";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TimeSheetController.update(@Valid TimeSheet timeSheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, timeSheet);
            return "timesheets/update";
        }
        uiModel.asMap().clear();
        timeSheetService.updateTimeSheet(timeSheet);
        return "redirect:/timesheets/" + encodeUrlPathSegment(timeSheet.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TimeSheetController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, timeSheetService.findTimeSheet(id));
        return "timesheets/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TimeSheetController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TimeSheet timeSheet = timeSheetService.findTimeSheet(id);
        timeSheetService.deleteTimeSheet(timeSheet);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/timesheets";
    }
    
    void TimeSheetController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("timeSheet_occurrencedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    String TimeSheetController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}

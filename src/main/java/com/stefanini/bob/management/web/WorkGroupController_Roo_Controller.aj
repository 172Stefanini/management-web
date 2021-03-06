// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stefanini.bob.management.web;

import com.stefanini.bob.management.domain.WorkGroup;
import com.stefanini.bob.management.services.WorkGroupService;
import com.stefanini.bob.management.web.WorkGroupController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect WorkGroupController_Roo_Controller {
    
    @Autowired
    WorkGroupService WorkGroupController.workGroupService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String WorkGroupController.create(@Valid WorkGroup workGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, workGroup);
            return "workgroups/create";
        }
        uiModel.asMap().clear();
        workGroupService.saveWorkGroup(workGroup);
        return "redirect:/workgroups/" + encodeUrlPathSegment(workGroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String WorkGroupController.createForm(Model uiModel) {
        populateEditForm(uiModel, new WorkGroup());
        return "workgroups/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String WorkGroupController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("workgroup", workGroupService.findWorkGroup(id));
        uiModel.addAttribute("itemId", id);
        return "workgroups/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String WorkGroupController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("workgroups", WorkGroup.findWorkGroupEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) workGroupService.countAllWorkGroups() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("workgroups", WorkGroup.findAllWorkGroups(sortFieldName, sortOrder));
        }
        return "workgroups/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String WorkGroupController.update(@Valid WorkGroup workGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, workGroup);
            return "workgroups/update";
        }
        uiModel.asMap().clear();
        workGroupService.updateWorkGroup(workGroup);
        return "redirect:/workgroups/" + encodeUrlPathSegment(workGroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String WorkGroupController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, workGroupService.findWorkGroup(id));
        return "workgroups/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String WorkGroupController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        WorkGroup workGroup = workGroupService.findWorkGroup(id);
        workGroupService.deleteWorkGroup(workGroup);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/workgroups";
    }
    
    void WorkGroupController.populateEditForm(Model uiModel, WorkGroup workGroup) {
        uiModel.addAttribute("workGroup", workGroup);
    }
    
    String WorkGroupController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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

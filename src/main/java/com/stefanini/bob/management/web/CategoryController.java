package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.Category;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/categorys")
@Controller
@RooWebScaffold(path = "categorys", formBackingObject = Category.class)
public class CategoryController {
}

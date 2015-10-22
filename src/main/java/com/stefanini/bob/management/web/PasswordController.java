package com.stefanini.bob.management.web;
import com.stefanini.bob.management.domain.Password;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/passwords")
@Controller
@RooWebScaffold(path = "passwords", formBackingObject = Password.class)
public class PasswordController {
}

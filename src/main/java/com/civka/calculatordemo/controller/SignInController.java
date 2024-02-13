package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserDTO;
import com.civka.calculatordemo.service.RegisterUserAuthorityService;
import com.civka.calculatordemo.service.RegisterUserService;
import com.civka.calculatordemo.service.WebUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    private final RegisterUserService registerUserService;
    private final RegisterUserAuthorityService registerUserAuthorityService;
    private final WebUserService webUserService;

    @Autowired
    public SignInController(RegisterUserService registerUserService, RegisterUserAuthorityService registerUserAuthorityService, WebUserService webUserService) {
        this.registerUserService = registerUserService;
        this.registerUserAuthorityService = registerUserAuthorityService;
        this.webUserService = webUserService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login-form";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {

        model.addAttribute("webUserDTO", new WebUserDTO());
        return "register-form";
    }

    @PostMapping("/processRegisterForm")
    public String processForm(@Valid @ModelAttribute(name = "webUserDTO") WebUserDTO webUserDTO,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register-form";
        } else {
            WebUser webUser = webUserService.createWebUser(webUserDTO);
            registerUserService.saveWebUser(webUser);
            registerUserAuthorityService.saveWebUserAuthority(webUser, "USER");
            return "redirect:/";
        }
    }
}

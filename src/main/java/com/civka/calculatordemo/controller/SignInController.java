package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserDTO;
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
    private final WebUserService webUserService;

    @Autowired
    public SignInController(RegisterUserService registerUserService, WebUserService webUserService) {
        this.registerUserService = registerUserService;
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
            return "login-form";
        } else {
            WebUser webUser = webUserService.createWebUser(webUserDTO);
            registerUserService.saveUser(webUser);
            registerUserService.saveAuthority(webUser, "USER");
            return "redirect:/";
        }
    }
}

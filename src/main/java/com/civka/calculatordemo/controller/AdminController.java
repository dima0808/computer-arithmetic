package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserAuthority;
import com.civka.calculatordemo.service.RegisterUserAuthorityService;
import com.civka.calculatordemo.service.RegisterUserService;
import com.civka.calculatordemo.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RegisterUserService registerUserService;
    private final RegisterUserAuthorityService registerUserAuthorityService;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public AdminController(RegisterUserService registerUserService,
                           RegisterUserAuthorityService registerUserAuthorityService,
                           UserAuthenticationService userAuthenticationService) {
        this.registerUserService = registerUserService;
        this.registerUserAuthorityService = registerUserAuthorityService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping
    public String getAdminPage(Model model) {

        model.addAttribute("userNickname", userAuthenticationService.getNicknameByAuth());

        List<WebUser> webUsers = registerUserService.findAll();
        List<WebUserAuthority> webUserAuthorities = registerUserAuthorityService.findAll();

        model.addAttribute("webUsers", webUsers);
        model.addAttribute("webUserAuthorities", webUserAuthorities);

        return "admin-page";
    }

    @GetMapping("/updateWebUser")
    public String getUpdateForm(@RequestParam("username") String username, Model model) {

        model.addAttribute("userNickname", userAuthenticationService.getNicknameByAuth());

        model.addAttribute("webUser", registerUserService.findByUsername(username));

        return "web-user-update";
    }

    @PostMapping("/saveWebUser")
    public String saveWebUser(@ModelAttribute("webUser") WebUser webUser) {


        webUser.setAuthorities(registerUserService.findByUsername(webUser.getUsername()).getAuthorities());

        registerUserService.saveWebUser(webUser);

        return "redirect:/admin";
    }

    @GetMapping("/deleteWebUser")
    public String deleteWebUser(@RequestParam("username") String username) {

        registerUserService.deleteWebUserByUsername(username);

        return "redirect:/admin";
    }

    @GetMapping("/addWebUserAuthority")
    public String addWebUserAuthority(Model model) {

        model.addAttribute("webUserAuthority", new WebUserAuthority());

        return "web-user-authority-add";
    }

    @PostMapping("/saveWebUserAuthority")
    public String saveWebUserAuthority(@ModelAttribute("webUserAuthority") WebUserAuthority webUserAuthority) {

        registerUserAuthorityService.saveWebUserAuthority(webUserAuthority);

        return "redirect:/admin";
    }

    @GetMapping("/deleteWebUserAuthority")
    public String deleteWebUserAuthority(@RequestParam("username") String username, @RequestParam("authority") String authority) {

        registerUserAuthorityService.deleteWebUserAuthority(new WebUserAuthority(username, authority));

        return "redirect:/admin";
    }
}

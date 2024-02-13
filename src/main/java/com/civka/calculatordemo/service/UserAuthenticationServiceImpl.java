package com.civka.calculatordemo.service;

import com.civka.calculatordemo.entity.LabData;
import com.civka.calculatordemo.entity.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final RegisterUserService registerUserService;

    @Autowired
    public UserAuthenticationServiceImpl(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @Override
    public LabData getLabDataByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if (authentication != null) username = authentication.getName();
        else return new LabData();

        WebUser webUser = null;
        if (!username.equals("anonymousUser")) webUser = registerUserService.findByUsername(username);

        if (webUser != null) return new LabData(webUser.getCreditNumber());
        else {
            return new LabData();
        }
    }

    @Override
    public String getNicknameByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if (authentication != null) username = authentication.getName();
        else return "failedUser";

        WebUser webUser;
        if (!username.equals("anonymousUser")) webUser = registerUserService.findByUsername(username);
        else return "anonymousUser";
        if (webUser != null) return webUser.getNickname();
        else {
            return "failedUser";
        }
    }
}

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
        String username = authentication.getName();
        if (username.equals("anonymousUser")) {
            return new LabData();
        } else {
            WebUser user = registerUserService.findByUsername(username);
            Integer creditNumber = user.getCreditNumber();
            return new LabData(creditNumber);
        }
    }

    @Override
    public String getNicknameByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (!username.equals("anonymousUser")) {
            return registerUserService.findByUsername(username).getNickname();
        } else {
            return null;
        }
    }
}

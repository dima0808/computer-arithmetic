package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.RegisterUserAuthorityRepository;
import com.civka.calculatordemo.dao.RegisterUserRepository;
import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final RegisterUserRepository registerUserRepository;
    private final RegisterUserAuthorityRepository registerUserAuthorityRepository;

    @Autowired
    public RegisterUserServiceImpl(RegisterUserRepository registerUserRepository,
                                   RegisterUserAuthorityRepository registerUserAuthorityRepository) {
        this.registerUserRepository = registerUserRepository;
        this.registerUserAuthorityRepository = registerUserAuthorityRepository;
    }

    @Override
    public WebUser saveUser(WebUser webUser) {
        return registerUserRepository.save(webUser);
    }

    @Override
    public WebUserAuthority saveAuthority(WebUser webUser, String authority) {
        return registerUserAuthorityRepository.save(new WebUserAuthority(webUser.getUsername(), "ROLE_" + authority));
    }
 }

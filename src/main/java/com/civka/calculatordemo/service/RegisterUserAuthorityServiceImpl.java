package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.RegisterUserAuthorityRepository;
import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterUserAuthorityServiceImpl implements RegisterUserAuthorityService {

    private final RegisterUserAuthorityRepository registerUserAuthorityRepository;

    @Autowired
    public RegisterUserAuthorityServiceImpl(RegisterUserAuthorityRepository registerUserAuthorityRepository) {
        this.registerUserAuthorityRepository = registerUserAuthorityRepository;
    }

    @Override
    public List<WebUserAuthority> findAll() {
        return registerUserAuthorityRepository.findAllByOrderById_AuthorityAsc();
    }

    @Override
    public List<WebUserAuthority> findByUsername(String username) {
        return null;
    }

    @Override
    public WebUserAuthority saveWebUserAuthority(WebUserAuthority webUserAuthority) {
        return registerUserAuthorityRepository.save(webUserAuthority);
    }

    @Override
    public WebUserAuthority saveWebUserAuthority(WebUser webUser, String authority) {
        return registerUserAuthorityRepository.save(new WebUserAuthority(webUser.getUsername(), "ROLE_" + authority));
    }

    @Override
    public void deleteWebUserAuthority(WebUserAuthority webUserAuthority) {
        registerUserAuthorityRepository.delete(webUserAuthority);
    }
}

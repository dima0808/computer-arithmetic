package com.civka.calculatordemo.service;

import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public WebUser createWebUser(WebUserDTO webUserDTO) {
        return new WebUser(webUserDTO.getUsername(),
                webUserDTO.getEmail(),
                webUserDTO.getCreditNumber(),
                passwordEncoder.encode(webUserDTO.getPassword()));
    }

}

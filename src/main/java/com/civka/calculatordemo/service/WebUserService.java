package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.RegisterUserRepository;
import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {

    private final PasswordEncoder passwordEncoder;
    private final RegisterUserRepository userRepository;

    @Autowired
    public WebUserService(PasswordEncoder passwordEncoder, RegisterUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public WebUser createWebUser(WebUserDTO webUserDTO) {
        return new WebUser(webUserDTO.getUsername(),
                webUserDTO.getEmail(),
                2518,
                passwordEncoder.encode(webUserDTO.getPassword()));
    }

    public WebUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

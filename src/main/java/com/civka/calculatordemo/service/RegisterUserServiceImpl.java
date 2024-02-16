package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.RegisterUserRepository;
import com.civka.calculatordemo.entity.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final RegisterUserRepository registerUserRepository;

    @Autowired
    public RegisterUserServiceImpl(RegisterUserRepository registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }

    @Override
    public WebUser saveWebUser(WebUser webUser) {
        return registerUserRepository.save(webUser);
    }

    @Override
    public List<WebUser> findAll() {
        return registerUserRepository.findAllByOrderByCreditNumberAsc();
    }

    @Override
    public WebUser findByUsername(String username) {
        Optional<WebUser> webUser = Optional.ofNullable(registerUserRepository.findByUsername(username));
        return webUser.orElse(null);
    }

    @Override
    @Transactional
    public void deleteWebUserByUsername(String username) {
        findByUsername(username);
        registerUserRepository.deleteById(username);
    }
}

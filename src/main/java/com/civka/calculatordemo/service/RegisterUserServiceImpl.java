package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.RegisterUserRepository;
import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.exception.WebUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final RegisterUserRepository registerUserRepository;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public RegisterUserServiceImpl(RegisterUserRepository registerUserRepository, SessionRegistry sessionRegistry) {
        this.registerUserRepository = registerUserRepository;
        this.sessionRegistry = sessionRegistry;
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
        if (webUser.isPresent()) {
            return webUser.get();
        } else {
            throw new WebUserNotFoundException("web user " + username + " not found.");
        }
    }

    @Override
    @Transactional
    public void deleteWebUserByUsername(String username) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User user) {
                if (user.getUsername().equals(username)) {
                    for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(principal, false)) {
                        sessionInformation.expireNow();
                    }
                }
            }
        }

        // Оновити контекст безпеки
        SecurityContextHolder.getContext().setAuthentication(null);
        findByUsername(username);
        registerUserRepository.deleteById(username);
    }
}

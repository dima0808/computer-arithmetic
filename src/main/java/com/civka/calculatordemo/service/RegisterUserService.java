package com.civka.calculatordemo.service;


import com.civka.calculatordemo.entity.WebUser;

import java.util.List;

public interface RegisterUserService {

    List<WebUser> findAll();

    WebUser findByUsername(String username);

    WebUser saveWebUser(WebUser webUser);

    void deleteWebUserByUsername(String username);
}

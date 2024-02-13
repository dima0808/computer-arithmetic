package com.civka.calculatordemo.service;


import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserAuthority;

public interface RegisterUserService {

    WebUser saveUser(WebUser webUser);

    WebUserAuthority saveAuthority(WebUser webUser, String authority);
}

package com.civka.calculatordemo.service;

import com.civka.calculatordemo.entity.WebUser;
import com.civka.calculatordemo.entity.WebUserAuthority;

import java.util.List;

public interface RegisterUserAuthorityService {

    List<WebUserAuthority> findAll();

    List<WebUserAuthority> findByUsername(String username);

    WebUserAuthority saveWebUserAuthority(WebUserAuthority webUserAuthority);

    WebUserAuthority saveWebUserAuthority(WebUser webUser, String authority);

    void deleteWebUserAuthority(WebUserAuthority webUserAuthority);
}

package com.civka.calculatordemo.dao;

import com.civka.calculatordemo.entity.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterUserRepository extends JpaRepository<WebUser, String> {

    WebUser findByUsername(String username);

    List<WebUser> findAllByOrderByCreditNumberAsc();
}

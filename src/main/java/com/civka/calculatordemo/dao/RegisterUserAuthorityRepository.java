package com.civka.calculatordemo.dao;

import com.civka.calculatordemo.entity.WebUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterUserAuthorityRepository extends JpaRepository<WebUserAuthority, WebUserAuthority.AuthorityId> {

    List<WebUserAuthority> findAllByOrderById_AuthorityAsc();
}

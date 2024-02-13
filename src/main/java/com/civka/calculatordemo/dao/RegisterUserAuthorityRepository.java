package com.civka.calculatordemo.dao;

import com.civka.calculatordemo.entity.WebUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterUserAuthorityRepository extends JpaRepository<WebUserAuthority, WebUserAuthority.AuthorityId> {
}

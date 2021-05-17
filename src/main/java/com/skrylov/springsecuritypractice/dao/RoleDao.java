package com.skrylov.springsecuritypractice.dao;

import com.skrylov.springsecuritypractice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
}

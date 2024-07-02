package com.api.portal_exame_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.portal_exame_backend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

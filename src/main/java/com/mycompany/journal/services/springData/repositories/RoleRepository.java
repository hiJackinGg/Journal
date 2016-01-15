package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
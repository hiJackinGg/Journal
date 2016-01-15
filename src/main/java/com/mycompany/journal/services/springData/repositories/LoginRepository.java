package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {

}
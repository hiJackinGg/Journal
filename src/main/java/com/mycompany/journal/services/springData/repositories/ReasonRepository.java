package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<Reason, Long> {

}
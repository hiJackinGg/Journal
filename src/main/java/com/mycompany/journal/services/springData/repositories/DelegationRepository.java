package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<Delegation, Long> {

}
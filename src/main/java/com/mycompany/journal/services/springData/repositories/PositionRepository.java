package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
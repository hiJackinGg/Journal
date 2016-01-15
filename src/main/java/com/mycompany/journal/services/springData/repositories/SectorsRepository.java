package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorsRepository extends JpaRepository<Sector, Long> {

}
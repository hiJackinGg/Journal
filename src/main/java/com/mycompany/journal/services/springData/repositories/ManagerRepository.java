package com.mycompany.journal.services.springData.repositories;

import com.mycompany.journal.db.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public interface ManagerRepository extends JpaRepository<Manager, Long > {

    List<Manager> findByFirstName(String title);

    List<Manager> findByLastName(String title);

    List<Manager> findByMiddleName(String title);

    List<Manager> findByPersonnel(String title);

    List<Manager> findByEmail(String title);

    List<Manager> findBySectorName(String title);

    List<Manager> findByPositionName(String title);


    @Modifying
    @Transactional
    @Query("delete from Manager u where u.id = ?1")
    Long deleteById(Long id);



}
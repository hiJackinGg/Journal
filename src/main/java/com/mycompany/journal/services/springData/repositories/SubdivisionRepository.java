package com.mycompany.journal.services.springData.repositories;




import com.mycompany.journal.db.model.Subdivision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubdivisionRepository extends JpaRepository<Subdivision, Long> {

}

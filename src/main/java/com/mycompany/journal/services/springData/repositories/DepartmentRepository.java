package com.mycompany.journal.services.springData.repositories;



import com.mycompany.journal.db.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

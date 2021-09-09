package com.demo.demo.repository;

import com.demo.demo.model.Department;
import com.demo.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select p from Department p where lower(p.code) like concat('%', :code, '%')")
    List<Department> search (String code);
}

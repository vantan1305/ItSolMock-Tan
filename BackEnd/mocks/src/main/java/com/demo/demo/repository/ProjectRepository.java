package com.demo.demo.repository;

import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Component
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p where lower(p.name) like concat('%', :name, '%')")
    List<Project>search (String name);

}

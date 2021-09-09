package com.demo.demo.repository;

import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
public class ProjectRepositoryImpl implements IProjectRepository{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Project> isDeleteProject(Long id) {

        String sql = "UPDATE  project \n" +
                "SET delete_flag = 0\n" +
                "WHERE 1=1; ";

        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        if (id != null) {
            sb.append("and id =:id");
        }
       return  null;
    }
}

package com.demo.demo.repository;

import com.demo.demo.model.Project;

import java.util.List;

public interface IProjectRepository {
    List<Project> isDeleteProject (Long id);
}

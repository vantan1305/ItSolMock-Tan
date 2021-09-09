package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Project;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<UpdateProject> search(HttpServletRequest request, UpdateProject updateProject);

    Boolean delete(HttpServletRequest request, Long id);

    Project addProjectToUser(long user_id, long project_id);

    Project addProjectToDepartment(Project project, long id);

    List<Project> findProjectsForUser(long id);

    List<Project> findProjects();

    List<Project> findByDeleteFlag();

    void deleteProjectFromUser(long user_id, long project_id);

    List<UpdateUser> findAllUser(HttpServletRequest httpServletRequest);

    UpdateProject isdeleteProject(long id);
}

package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Project;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<UpdateProject> search(HttpServletRequest request, UpdateProject updateProject);

    Boolean delete(HttpServletRequest request, Long id);

}

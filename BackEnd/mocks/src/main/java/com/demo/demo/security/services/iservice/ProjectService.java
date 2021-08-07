package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProjectService {
    List<UpdateProject> search(HttpServletRequest request, UpdateProject updateProject);
}

package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateDepartment;
import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.model.Department;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DepartmentService {

//    Department editDepartment(Department department, long id);

    Boolean delete(HttpServletRequest request, Long id);

    List<UpdateDepartment> search(HttpServletRequest request, UpdateDepartment updateDepartment);
}

package com.demo.demo.security.services;

import com.demo.demo.message.request.UpdateDepartment;
import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.model.Department;
import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import com.demo.demo.repository.DepartmentRepository;
import com.demo.demo.repository.ProjectRepository;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.security.services.iservice.DepartmentService;
import com.demo.demo.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Component
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public UpdateDepartment addDepartment(HttpServletRequest httpServletRequest, Object object) {
        UpdateDepartment updateDepartment =(UpdateDepartment) object;
        Department department;
        if (updateDepartment != null){

//            Users users = AppUtil.NVL (updateDepartment.getUserId ()) == 0L ? null:
//                    userRepository.findById (updateDepartment.getUserId ()).orElse (null);
//
//            Project project = AppUtil.NVL (updateDepartment.getProjectId ()) == 0L?null:
//                    projectRepository.findById (updateDepartment.getProjectId ()).orElse (null);

            if (AppUtil.NVL (updateDepartment.getId ()) == 0L){

//                department = AppUtil.mapperEntAndDto (updateDepartment, Department.class);
//                department.setProjectsList ((List<Project>) project);
//                department.setUsersList ((List<Users>) users);
                department = AppUtil.mapperEntAndDto (updateDepartment, Department.class);
                department.setUsersList ((List<Users>) userRepository.findById (updateDepartment.getUserId ()).orElse (null));
                department.setProjectsList ((List<Project>) projectRepository.findById (updateDepartment.getProjectId ()).orElse (null));
                department.setCreateAt (new Date());
            }else{
                department = departmentRepository.findById (updateDepartment.getId ()).orElse (null);
                if (department == null){
                    Department data =AppUtil.mapperEntAndDto (updateDepartment, Department.class);
                    data.setId (department.getId ());
                    data.setCreateAt (new Date (  ));
                    department.setProjectsList ((List<Project>) projectRepository.findById (updateDepartment.getProjectId ()).orElse (null));
                    department.setUsersList ((List<Users>) userRepository.findById (updateDepartment.getUserId ()).orElse (null));
                    department = data;
                }
            }
            return AppUtil.mapperEntAndDto (departmentRepository.save (department), UpdateDepartment.class);
        }
        return null;
    }

    @Transactional
    public UpdateDepartment editDepartment(HttpServletRequest httpServletRequest, UpdateDepartment updateDepartment){
        Department department;
        if(updateDepartment != null){
            if(AppUtil.NVL (updateDepartment.getId ())== 0L ){
                department = AppUtil.mapperEntAndDto (updateDepartment, Department.class);
                department.setProjectsList ((List<Project>) projectRepository.findById (updateDepartment.getProjectId ()).orElse (null));
                department.setUsersList ((List<Users>) userRepository.findById (updateDepartment.getUserId ()).orElse (null));
            }else {
                department = departmentRepository.findById (updateDepartment.getId ()).orElse (null);
                if (department != null){
                    department.setCode (updateDepartment.getCode ());
                    department.setDescription (updateDepartment.getDescription ());
                    department.setCreateAt (new Date ());
                    department.setProjectsList ((List<Project>) projectRepository.findById (updateDepartment.getProjectId ()).orElse (null));
                    department.setUsersList ((List<Users>) userRepository.findById (updateDepartment.getUserId ()).orElse (null));
                }
            }
            return AppUtil.mapperEntAndDto(departmentRepository.save(department), UpdateDepartment.class);
        }
        return null;
    }

    @Transactional
    public Optional<Department> findDepartment(Long id) {
        return departmentRepository.findById (id);
    }

//    @Override
//    public void deleteDepartment(long id) {
//        departmentRepository.deleteById (id);
//    }

    @Override
    public Boolean delete(HttpServletRequest request, Long id) {
        Department department = departmentRepository.findById (id).orElse (null);
        if (department != null){
            departmentRepository.delete (department);
            return true;
        }
        return false;
    }

    @Override
    public List<UpdateDepartment> search(HttpServletRequest request, UpdateDepartment updateDepartment) {
        return departmentRepository.search (updateDepartment.getCode ().toLowerCase ())
                .stream ()
                .map (obj ->{
                    UpdateDepartment updateDepartment1 = AppUtil.mapperEntAndDto (obj, UpdateDepartment.class);
                    return updateDepartment1;
                }).collect (Collectors.toList ( ));
    }

    @Transactional
    public Iterable<Department> findAll() {
        return departmentRepository.findAll();
    }

}

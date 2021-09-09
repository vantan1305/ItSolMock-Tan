package com.demo.demo.security.services;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Department;
import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import com.demo.demo.repository.DepartmentRepository;
import com.demo.demo.repository.IProjectRepository;
import com.demo.demo.repository.ProjectRepository;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.security.services.iservice.ProjectService;
import com.demo.demo.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private IProjectRepository iProjectRepository;

    @Override
    public List<Project> findByDeleteFlag() {
        return projectRepository.findByDeleteFlag (true);
    }

    @Transactional
    public UpdateProject updateProject(HttpServletRequest request, UpdateProject updateProject){
        Project project;
        // luu them moi
        if (updateProject != null){
            if(AppUtil.NVL(updateProject.getId())==0L){
                project = AppUtil.mapperEntAndDto(updateProject, Project.class);
<<<<<<< HEAD
//                project.setDepartment (departmentRepository.findById (updateProject.getDepartmentId ()).orElse (null));
//                project.setUsers (userRepository.findById (updateProject.getUserId ()).orElse (null));
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
            }
            //  update
            else {
                project = projectRepository.findById(updateProject.getId()).orElse(null);
                if (project != null){
//                    Project project1 = AppUtil.mapperEntAndDto (updateProject, Project.class);
                    project.setUnit (updateProject.getUnit ());
                    project.setName (updateProject.getName ());
                    project.setDescription (updateProject.getDescription ());
                    project.setStatus (updateProject.getStatus ());
                    project.setTimeEnd (updateProject.getTimeEnd ());
                    project.setTimeStart (updateProject.getTimeStart ());
<<<<<<< HEAD
//                    project.setDepartment (departmentRepository.findById (updateProject.getDepartmentId ()).orElse (null));
//                    project.setUsers (userRepository.findById (updateProject.getUserId ()).orElse (null));
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
                }
            }
            return  AppUtil.mapperEntAndDto(projectRepository.save(project), UpdateProject.class);
        }
        return  null;
    }

    @Transactional
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }


    @Transactional
    public Optional<Project> findById(Long id) {
        return projectRepository.findById (id);
    }

    @Override
    public List<UpdateProject> search(HttpServletRequest request, UpdateProject updateProject) {
        return projectRepository.search (updateProject.getName ().toLowerCase ())
                .stream ()
                .map (obj ->{
                    UpdateProject updateProject1 = AppUtil.mapperEntAndDto (obj, UpdateProject.class);
                    return updateProject1;
                }).collect (Collectors.toList ( ));
    }


//    @Override
//    public void editProject(Project project) {
//        this.projectRepository.editProject(project.getDescription (), project.getName (), project.getStatus (),
//                project.getTimeEnd (), project.getTimeStart (), project.getUnit (), project.getId ());
//    }

    @Override
    public Boolean delete(HttpServletRequest request, Long id) {
        Project project = projectRepository.findById (id).orElse (null);
        if(project != null){
            projectRepository.delete (project);
            return true;
        }
        return false;
    }
<<<<<<< HEAD
//new
    @Override
    public Project addProjectToUser(long user_id, long project_id) {
        Users users = userRepository.findById (user_id).get ();
        Project project = projectRepository.findById (project_id).get ();
        users.addProjectToUser (project);
        return projectRepository.save (project);
    }

    @Override
    public Project addProjectToDepartment(Project project, long id) {
        Department department = departmentRepository.findById (id).get ();
        project.setTimeStart (new Date());
        department.addProjectToDepartment (project);
        return projectRepository.save (project);
    }

    @Override
    public List<Project> findProjectsForUser(long id) {
        Users users = userRepository.findById (id).get ();
        return users.getProjects ();
    }

    @Override
    public List<Project> findProjects() {
        return projectRepository.findAll ();
    }



    @Override
    public void deleteProjectFromUser(long user_id, long project_id) {
        Users users = userRepository.findById (user_id).get ();
        Project project = projectRepository.findById (project_id).get ();
        users.getProjects ().remove (project);
    }

    @Override
    public List<UpdateUser> findAllUser(HttpServletRequest httpServletRequest) {
        return userRepository.findAll ().stream ()
                .map (obj -> AppUtil.mapperEntAndDto (obj, UpdateUser.class))
                .collect (Collectors.toList ());
    }

    @Override
    public UpdateProject isdeleteProject(long id) {
//        UpdateProject dto = new UpdateProject ();
        Project projectEntity;
        if(AppUtil.NVL (id)==0L){
           return null;
                                }
        else {
            projectEntity = projectRepository.findById(id).orElse(null);
            if (projectEntity != null){
//                Project dataProject = AppUtil.mapperEntAndDto(dto,Project.class);
                projectEntity.setDeleteFlag (false);
//                projectEntity = dataProject;
            }
            return  AppUtil.mapperEntAndDto(projectRepository.save(projectEntity), UpdateProject.class);
        }

    }
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
}

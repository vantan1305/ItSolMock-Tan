package com.demo.demo.security.services;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Project;
import com.demo.demo.model.Users;
import com.demo.demo.repository.ProjectRepository;
import com.demo.demo.security.services.iservice.ProjectService;
import com.demo.demo.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Transactional
    public UpdateProject updateProject(HttpServletRequest request, UpdateProject updateProject){
        Project project;
        // luu them moi
        if (updateProject != null){
            if(AppUtil.NVL(updateProject.getId())==0L){
                project = AppUtil.mapperEntAndDto(updateProject, Project.class);
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
}

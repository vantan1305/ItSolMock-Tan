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
//                Users users = AppUtil.NVL(updateProject.getUserId ()) == 0L ? null :
//                        userDetailsService.findById(updateProject.getUserId ()).orElse(null);
            }
            //  update
            else {
                project = projectRepository.findById(updateProject.getId()).orElse(null);
                if (project != null){
                    Project project1 = AppUtil.mapperEntAndDto (updateProject, Project.class);
                    project1.setName (updateProject.getName ());
                    project1.setDescription (updateProject.getDescription ());
                    project1.setStatus (updateProject.getStatus ());
                    project1.setTimeEnd (updateProject.getTimeEnd ());
                    project1.setTimeStart (updateProject.getTimeStart ());
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
}

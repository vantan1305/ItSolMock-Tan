package com.demo.demo.controller;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.model.Project;
import com.demo.demo.repository.ProjectRepository;
import com.demo.demo.security.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @PutMapping("/update")
    public ResponseEntity saveOrUpdate(HttpServletRequest request, UpdateProject updateProject){
        return ResponseEntity.ok().body(projectService.updateProject (request, updateProject));
    }

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok().body(projectService.findAll ());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return  ResponseEntity.ok ( ).body (projectService.findById (id));
    }

    @GetMapping("/search")
    public ResponseEntity search(HttpServletRequest request, UpdateProject updateProject){
        return  ResponseEntity.ok ().body (projectService.search (request, updateProject));
    }

//    @PutMapping("/editProject/{id}")
//    public ResponseEntity<?> editProject(@Valid @RequestBody UpdateProject updateProject, @PathVariable long id){
//       Project project = projectService.findById (updateProject.getId ()).orElse(  null);
//
//        if( project == null){
//            return new ResponseEntity<> (HttpStatus.NO_CONTENT);
//        }else {
//            project.setId (id);
//            project.setUnit (updateProject.getUnit ());
//            project.setTimeStart (updateProject.getTimeStart ());
//            project.setTimeEnd (updateProject.getTimeEnd ());
//            project.setDescription (updateProject.getDescription ());
//            project.setName (updateProject.getName ());
//            project.setStatus (updateProject.getStatus ());
//            projectService.editProject (project);
//            return new ResponseEntity<> (HttpStatus.OK);
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProjectById(HttpServletRequest request,@PathVariable Long id){

        Map<String, String> responseData = new HashMap<> ();
        String message;
        Boolean result = projectService.delete(request, id);
        if (result) {
            message = "Delete success!";
            responseData.put("message", message);
            return ResponseEntity.ok().body(responseData);
        }
        return ResponseEntity.notFound().build();
//        Project project = this.projectService.findById (id).orElse (null);
//        if(project.getId () != null){
//            project.setDeleteFlag (true);
//            this.projectService.editProject (project);
//            return new ResponseEntity<> (HttpStatus.OK);
//        }else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
    }

}

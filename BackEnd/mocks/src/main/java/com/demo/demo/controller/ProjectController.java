package com.demo.demo.controller;

import com.demo.demo.message.request.UpdateProject;
<<<<<<< HEAD
import com.demo.demo.message.response.DeleteReponse;
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
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

    @PostMapping("/isdelete/{id}")
    public ResponseEntity deleteProject(HttpServletRequest request, @PathVariable Long id){
        return ResponseEntity.ok().body(projectService.isdeleteProject (id));
    }

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok().body(projectService.findByDeleteFlag ());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity getProjectById(@PathVariable Long id){
        return  ResponseEntity.ok ( ).body (projectService.findById (id));
    }

    @GetMapping("/search")
    public ResponseEntity search(HttpServletRequest request, UpdateProject updateProject){
        return  ResponseEntity.ok ().body (projectService.search (request, updateProject));
    }

<<<<<<< HEAD
=======
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

>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
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
<<<<<<< HEAD
    }

    @PostMapping("/addProjectToUser/{user_id}/{project_id}")
    Project addProjectToUser(@PathVariable long user_id, @PathVariable long project_id) {
        return projectService.addProjectToUser(user_id, project_id);
    }

    @PostMapping("/addProjectToDepartment/{id}")
    Project addProjectToDepartment(@RequestBody Project project, @PathVariable long id) {
        return projectService.addProjectToDepartment(project, id);
    }

    @GetMapping("/allUser")
    public ResponseEntity allUser(HttpServletRequest httpServletRequest){
        return ResponseEntity.ok (  ).body (projectService.findAllUser (httpServletRequest));
=======
//        Project project = this.projectService.findById (id).orElse (null);
//        if(project.getId () != null){
//            project.setDeleteFlag (true);
//            this.projectService.editProject (project);
//            return new ResponseEntity<> (HttpStatus.OK);
//        }else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
    }

}

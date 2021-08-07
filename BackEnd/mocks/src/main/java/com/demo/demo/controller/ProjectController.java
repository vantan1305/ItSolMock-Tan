package com.demo.demo.controller;

import com.demo.demo.message.request.UpdateProject;
import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.security.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

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
}

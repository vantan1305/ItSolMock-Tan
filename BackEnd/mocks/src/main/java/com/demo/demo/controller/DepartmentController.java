package com.demo.demo.controller;


import com.demo.demo.message.request.UpdateDepartment;
import com.demo.demo.model.Department;
import com.demo.demo.security.services.DepartmentServiceImpl;
import com.demo.demo.security.services.iservice.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @PostMapping("/addDepartment")
    public ResponseEntity addDepartment(HttpServletRequest request, UpdateDepartment updateDepartment){
        return ResponseEntity.ok ().body (departmentServiceImpl.addDepartment (request, updateDepartment));
    }

    @PutMapping("/editDepartment")
    public ResponseEntity editDepartment(HttpServletRequest httpServletRequest, UpdateDepartment updateDepartment) {
        return ResponseEntity.ok (  ).body (departmentServiceImpl.editDepartment(httpServletRequest,updateDepartment));
    }

    @GetMapping("/findDepartment/{id}")
    public ResponseEntity findDepartment(@PathVariable long id) {
        return ResponseEntity.ok (  ).body (departmentServiceImpl.findDepartment (id));
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity deleteDepartment(HttpServletRequest request,@PathVariable long id) {
        Map<String, String> responseData = new HashMap<> ();
        String message;
        Boolean result = departmentServiceImpl.delete (request, id);
        if (result) {
            message = "Delete success!";
            responseData.put("message", message);
            return ResponseEntity.ok().body(responseData);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok ().body (departmentServiceImpl.findAll ());
    }

    @GetMapping("/search")
    public ResponseEntity search(HttpServletRequest httpServletRequest, UpdateDepartment updateDepartment){
        return ResponseEntity.ok (  ).body (departmentServiceImpl.search (httpServletRequest, updateDepartment));
    }
}

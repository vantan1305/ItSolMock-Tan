package com.demo.demo.controller;


import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.ResponseMessage;
import com.demo.demo.model.Users;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.security.services.UserDetailsServiceImpl;
import com.demo.demo.security.services.iservice.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/role/user")
@PreAuthorize("hasRole('USER')or hasRole('ADMIN')or hasRole('MANAGER')or hasRole('PM')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UsersService usersService;

    @Autowired
    ServletContext context;


    @GetMapping("/all")
    public ResponseEntity findAll(){
        return ResponseEntity.ok().body(usersService.findByDeleteUser ());
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return  ResponseEntity.ok ( ).body (userDetailsService.findById (id));
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseMessage> updateUser(@RequestParam("file") MultipartFile file, @RequestParam("users") String users) throws IOException {

        Users users1 = new ObjectMapper (  ).readValue (users, Users.class);

        users1.setFileName (file.getBytes ());
        users1.setAvatar (file.getOriginalFilename ());
        Users dbUsers = userDetailsService.save(users1);

        if(dbUsers != null){
            return new ResponseEntity<ResponseMessage> (new ResponseMessage ("OK"), HttpStatus.OK);
        }else {
            return new ResponseEntity<ResponseMessage> (new ResponseMessage ("FALSE"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateProfileInServer")
    public ResponseEntity<ResponseMessage> updateProfileInServer(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam("users") String users) throws IOException {
        Users users1 = new ObjectMapper().readValue(users, Users.class) ;

        boolean isExist = new File (context.getRealPath ("/userProfile/")).exists ();
        if (!isExist){
            new File (context.getRealPath ("/userProfile/")).mkdir ();
        }

        String avatar = file.getOriginalFilename ();
        String modifiedFileName = FilenameUtils.getBaseName (avatar)+"_"+System.currentTimeMillis ()+
                "."+FilenameUtils.getExtension (avatar);
        File serverFile = new File (context.getRealPath ("/userProfile/" + File.separator + modifiedFileName));
        try{
            FileUtils.writeByteArrayToFile (serverFile, file.getBytes ());
        }catch (Exception e){
            e.printStackTrace ();
        }

        users1.setAvatar (modifiedFileName);
         Users dbUsers = userDetailsService.save(users1);
        if(dbUsers != null){
            return new ResponseEntity<ResponseMessage> (new ResponseMessage ("OK"), HttpStatus.OK);
        }else {
            return new ResponseEntity<ResponseMessage> (new ResponseMessage ("FALSE"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/updateUser1")
    public ResponseEntity saveOrUpdate(HttpServletRequest request, UpdateUser dto) {
        return ResponseEntity.ok().body(userDetailsService.updateUser2(request, dto));
    }


    @GetMapping(path="/ImgUsers/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        System.out.println("Get all Users Images...");
        Users Users   = userDetailsService.findById(id).get ();
        return Files.readAllBytes(Paths.get(context.getRealPath("/ImgUsers/")+Users.getAvatar ()));
    }


    @GetMapping("/search")
    public ResponseEntity search(HttpServletRequest request, UpdateUser updateUser){
        return  ResponseEntity.ok ().body (userDetailsService.search (request, updateUser));
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity searchByEmail(HttpServletRequest request, UpdateUser updateUser){
        return  ResponseEntity.ok ().body (userDetailsService.searchEmail (request, updateUser));
    }

    @GetMapping("/searchBySpecialized")
    public ResponseEntity searchBySpecialized(HttpServletRequest request, UpdateUser updateUser){
        return  ResponseEntity.ok ().body (userDetailsService.searchSpecialized (request, updateUser));
    }

    @PostMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(HttpServletRequest request, @PathVariable long id){
        return ResponseEntity.ok ().body (userDetailsService.isDeleteUser (id));
    }

}

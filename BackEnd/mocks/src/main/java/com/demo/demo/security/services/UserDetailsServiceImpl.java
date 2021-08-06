package com.demo.demo.security.services;


import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Users;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.security.services.iservice.UsersService;
import com.demo.demo.util.AppUtil;
import javassist.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.URIParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService, UsersService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ServletContext context;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Users> loadAll() {
        System.out.println ("Get all Users...");
        return userRepository.findAll (Sort.by ("userName").ascending ( ));
    }


    @Transactional
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }


    @Transactional
    public Optional<Users> findById(Long id) {
        return userRepository.findById (id);
    }



    @Transactional
    public Optional<Users> findByUsername(String userName) {
        return userRepository.findByUserName (userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException , DataAccessException {
        Users users = userRepository.findByUserName (userName).orElseThrow (
                () -> new UsernameNotFoundException ("User Not Found with -> username or email : " + userName));
        return UserPrinciple.build (users);
    }

    @Transactional
    public UpdateUser updateUser2(HttpServletRequest request, UpdateUser users){
        Users usersEntity ;
        if(users != null){
            //lưu thêm mới
            if(AppUtil.NVL(users.getId())==0L){
                usersEntity = AppUtil.mapperEntAndDto(users, Users.class);
            }
            //update
            else {
                usersEntity = userRepository.findById(users.getId()).orElse(null);
                if (usersEntity != null){
                    usersEntity.setEmail (users.getEmail ());
                    usersEntity.setAvatar ("assets/images/" +users.getAvatar ());
                    usersEntity.setSex (users.getSex ());
                    usersEntity.setEducation (users.getEducation ());
                    usersEntity.setLastName (users.getLastName ());
                    usersEntity.setMiddleName (users.getMiddleName ());
                    usersEntity.setFirstName (users.getFirstName ());
                    usersEntity.setDob (users.getDob ());
                    usersEntity.setHomeTown (users.getHomeTown ());
                    usersEntity.setPhone (users.getPhone ());
                    usersEntity.setSchool (users.getSchool ());
                    usersEntity.setSpecialized (users.getSpecialized ());
                    usersEntity.setStaffType (users.getStaffType ());
                    usersEntity.setIdentityCard (users.getIdentityCard ());
                }
            }
            return  AppUtil.mapperEntAndDto(userRepository.save(usersEntity), UpdateUser.class);
        }
        return null;
    }

    @Transactional
    public void addUserImage(MultipartFile file)
    {
        boolean isExit = new File (context.getRealPath("/ImgUsers/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/ImgUsers/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        String avatar = file.getOriginalFilename();
        String newAvatar = FilenameUtils.getBaseName(avatar)+"."+FilenameUtils.getExtension(avatar);
        File serverFile = new File (context.getRealPath("/ImgUsers/"+File.separator+newAvatar));
        try
        {

            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            System.out.println("Failed to Add Image User !!");
        }
    }

    @Transactional
    public boolean isAccountVerified(String email) {
        boolean isVerified = userRepository.findEmailVerifiedByEmail (email);
        return isVerified;
    }

    @Transactional
    public Users save(Users users1) {
        return userRepository.save (users1);
    }

    @Override
    public List<UpdateUser> search(HttpServletRequest request, UpdateUser updateUser){
        return userRepository.search (updateUser.getUserName ().toLowerCase ())
                .stream ()
                .map (obj ->{
                    UpdateUser updateUser1 = AppUtil.mapperEntAndDto (obj, UpdateUser.class);
                    return updateUser1;
                }).collect (Collectors.toList ( ));
    }
}

package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateUser;
import com.demo.demo.model.Users;
import com.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.security.URIParameter;
import java.util.List;

public interface UsersService {


    List<UpdateUser> search(HttpServletRequest request, UpdateUser updateUser);
    List<UpdateUser> searchEmail(HttpServletRequest request, UpdateUser updateUser);
    List<UpdateUser> searchSpecialized(HttpServletRequest request, UpdateUser updateUser);

    Users addUserToDepartment(Users users, Long id);

    UpdateUser isDeleteUser(long id);

    List<Users> findByDeleteUser();

}

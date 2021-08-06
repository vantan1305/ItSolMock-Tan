package com.demo.demo.security.services.iservice;

import com.demo.demo.message.request.UpdateUser;

import javax.servlet.http.HttpServletRequest;
import java.security.URIParameter;
import java.util.List;

public interface UsersService {
    List<UpdateUser> search(HttpServletRequest request, UpdateUser updateUser);

}

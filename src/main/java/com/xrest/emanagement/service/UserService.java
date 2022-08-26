package com.xrest.emanagement.service;

import com.xrest.emanagement.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService , BaseService<User,Long>{
    User register(User user);
}

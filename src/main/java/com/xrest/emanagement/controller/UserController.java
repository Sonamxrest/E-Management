package com.xrest.emanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrest.emanagement.dto.LoginDto;
import com.xrest.emanagement.entity.User;
import com.xrest.emanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/user")
public class UserController extends BaseController<User,Long> {
   private final  PasswordEncoder passwordEncoder;
   private final UserService userService;
   private ObjectMapper objectMapper = new ObjectMapper();
    public UserController( @Autowired PasswordEncoder passwordEncoder, UserService userService) {
        super(userService);
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody LoginDto obj) {
        User user = objectMapper.convertValue(obj, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.register(user);
        return  user == null ? new ResponseEntity<String>("User Already Exist", HttpStatus.BAD_REQUEST) :new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping("/getA")
    private ResponseEntity<?> getAl() {
        return  new ResponseEntity<List>(userService.findAll(), HttpStatus.OK);
    }
}

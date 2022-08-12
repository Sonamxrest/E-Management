package com.xrest.emanagement.controller;

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
    public UserController( @Autowired PasswordEncoder passwordEncoder, UserService userService) {
        super(userService);
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }
    @GetMapping("/getA")
    private ResponseEntity<?> getAl() {
        return  new ResponseEntity<List>(userService.findAll(), HttpStatus.OK);
    }
}

package com.codegym.findJob.controller;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.IUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    IUserService2 userService;


    @GetMapping("/findAllUser")
    public ResponseEntity<List<Users>> getAllUser() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id, @RequestBody Users users) {
        users.setId(id);
        userService.saveUser(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}

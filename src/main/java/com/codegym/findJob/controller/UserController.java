package com.codegym.findJob.controller;
import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    IUserService userService;

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
        userService.saveEdit(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/search/post")
    public ResponseEntity<Set<Post>> search(@RequestBody SearchForm searchForm) {
        return new ResponseEntity(userService.search(searchForm), HttpStatus.OK);
    }

}

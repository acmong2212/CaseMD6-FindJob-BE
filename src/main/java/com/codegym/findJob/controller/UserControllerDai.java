package com.codegym.findJob.controller;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.service.IPostServiceDai;
import com.codegym.findJob.service.IUserServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userDai")
@AllArgsConstructor
public class UserControllerDai {
    IUserServiceDai userServiceDai;
    IPostServiceDai postServiceDai;


    @GetMapping("/getPostByKeyword/{keyword}") //tính năng 10
    private ResponseEntity<List<Post>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(userServiceDai.findPostByKeyword(keyword), HttpStatus.OK);
    }

    //tính năng 16: feature-16-find-post-by-field
    @GetMapping("/getPostByField/{idField}")
    public ResponseEntity<List<Post>> finPostByField(@PathVariable Long idField){
        return new ResponseEntity<>(postServiceDai.findByField(idField),HttpStatus.OK);
    }
}

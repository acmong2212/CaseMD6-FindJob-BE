package com.codegym.findJob.controller;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

    @Autowired
    IPostService postService;

    @GetMapping("/findAllPost")
    public ResponseEntity<List<Post>> getAllPost() {
        return new ResponseEntity<>(postService.findAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findPostById(id), HttpStatus.OK);
    }



}

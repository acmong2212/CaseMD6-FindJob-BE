package com.codegym.findJob.controller;

import com.codegym.findJob.dto.request.SearchForm;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.IPostService;
import com.codegym.findJob.service.IUserServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userDai")
@AllArgsConstructor
public class UserControllerDai {
    IUserServiceDai userServiceDai;

    IPostService postService;

    @GetMapping("/getPostByKeyword/{keyword}") //tính năng 10
    private ResponseEntity<List<Post>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(userServiceDai.findPostByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/search")
        public ResponseEntity<List<Post>> search(@RequestBody SearchForm searchForm){
        return new ResponseEntity<>(postService.search(searchForm), HttpStatus.OK);
    }
}

package com.codegym.findJob.controller;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.IPostService;
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

    IPostService postService;

    @GetMapping("/getPostByKeyword/{keyword}") //tính năng 10
    private ResponseEntity<List<Post>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(userServiceDai.findPostByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> search(@PathVariable String title, @PathVariable String companyName, @PathVariable String address,@PathVariable Long idField,@PathVariable Double minSalary, @PathVariable Double maxSalary){
        return new ResponseEntity<>(postService.search(title, companyName, address, idField,minSalary, maxSalary), HttpStatus.OK);
    }
}

package com.codegym.findJob.controller;

import com.codegym.findJob.model.Post;
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


    @GetMapping("/getPostByKeyword/{keyword}") //tính năng 10
    public ResponseEntity<List<Post>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(userServiceDai.findPostByKeyword(keyword), HttpStatus.OK);
    }

    //11. Là người dùng tôi muốn tìm kiếm job theo ngành nghề, địa chỉ.
    @GetMapping("/getPostByFieldAndAddress/{idField}&&{address}")
    public ResponseEntity<List<Post>> findByFieldAndAddress( @PathVariable Long idField, @PathVariable String address){
        return new ResponseEntity<>(userServiceDai.findByFieldAndAddress(address, idField), HttpStatus.OK);
    }
}

package com.codegym.findJob.controller;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.ICompanyRepository;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IUserServiceDai;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userDai")
@AllArgsConstructor
public class UserControllerDai {
    IUserServiceDai userServiceDai;

    ICompanyService companyService;

    @GetMapping("/getPostByKeyword/{keyword}") //tính năng 10
    private ResponseEntity<List<Post>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(userServiceDai.findPostByKeyword(keyword), HttpStatus.OK);
    }

    //15. Là người dùng, tôi muốn xem được top các công ty đang có nhu cầu tuyển dụng cao nhất.
    @GetMapping("")
    public ResponseEntity<Set<Post>> highestRecruitmentDemand(){
        return new ResponseEntity<>(companyService.highestRecruitmentDemand(), HttpStatus.OK);
    }
}

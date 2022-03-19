package com.codegym.findJob.controller;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.Field;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.IFieldService;
import com.codegym.findJob.service.ICompanyService;
import com.codegym.findJob.service.IPostService;
import com.codegym.findJob.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    ICompanyService companyService;

    IFieldService fieldService;

    IPostService postService;

    IUserService userService;

    //get Field list
    @GetMapping("/field")
    public ResponseEntity<List<Field>> getAllField() {
        return new ResponseEntity<>(fieldService.findAll(), HttpStatus.OK);
    }

    //COMPANY
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    //find Company by Id
    @GetMapping("/{id}")
    public Optional<Company> findCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    //edit Company Profile
    @PutMapping("")
    public ResponseEntity<?> editCompanyInformation(@RequestBody Company company) {
        companyService.saveEdit(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    ------------------
//    POST

    //get post by company
    @GetMapping("/postByCompanyId/{idCompany}")
    public ResponseEntity<?> findPostByIdCompany(@PathVariable Long idCompany, @PageableDefault Pageable pageable) {
        Page<Post> page = postService.findPostByIdCompany(idCompany, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    //find Post by Id
    @GetMapping("/post/{id}")
    public Post findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    //create Post
    @PostMapping("/post/create")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //edit Post
    @PutMapping("/post/edit/{id}")
    public ResponseEntity<?> editPost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //block Status
    @PutMapping("/post/status/{id}")
    public ResponseEntity<?> editStatusPost(@PathVariable Long id) {
        postService.setStatusPost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Thao
    //ham hien thi so luong apply bai post cua cong ty
    @GetMapping("/post/count/{postId}")
        public List<Users> countApplyByPost(@PathVariable Long postId){
        List<Long> userIdList = companyService.countApplyByPost(postId);
        List<Users> userAppliedList = new ArrayList<>();
        for (Long aLong : userIdList) {
            userAppliedList.add(userService.findById(aLong));
        }
        return userAppliedList;
    }

}
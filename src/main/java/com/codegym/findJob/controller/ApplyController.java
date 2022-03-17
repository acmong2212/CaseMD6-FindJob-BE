package com.codegym.findJob.controller;

import com.codegym.findJob.email.ICompanyNotificationService;
import com.codegym.findJob.model.Apply;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.IApplyService;
import com.codegym.findJob.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/apply")
public class ApplyController {

    private IUserService userService;

    private IApplyService applyService;

    @GetMapping("/list")
    public ResponseEntity<?> showApply() {
        Users users = userService.getUsersPriciple();
        List<Apply> applyList = applyService.listApply(users);
        return new ResponseEntity<>(applyList, HttpStatus.OK);
    }

    @PostMapping("/add/{pid}")
    public ResponseEntity<?> addPostToApply(@PathVariable("pid") Long postId) {
        Users users = userService.getUsersPriciple();
        applyService.addPost(postId, users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

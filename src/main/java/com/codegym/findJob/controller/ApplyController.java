package com.codegym.findJob.controller;

import com.codegym.findJob.model.Apply;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.model.Users;
import com.codegym.findJob.service.IApplyService;
import com.codegym.findJob.service.IPostService;
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

    private IPostService postService;

    @GetMapping("/list")
    public ResponseEntity<?> showApply() {
        Users users = userService.getUsersPriciple();
        List<Apply> applyList = applyService.listApply(users);
        return new ResponseEntity<>(applyList, HttpStatus.OK);
    }

    // Apply khi có đính token vào request
    @PostMapping("/add/{pid}")
    public ResponseEntity<?> addPostToApply(@PathVariable("pid") Long postId) {
        Users users = userService.getUsersPriciple();
        applyService.addPost(postId, users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Apply khi không đính token vào request
//    @PostMapping("/add/{pid}")
//    public ResponseEntity<?> addPostToApply(@PathVariable("pid") Long postId,@RequestParam Long userId) {
//        Users users = userService.findUserById(userId);
//        applyService.addPost(postId, users);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/list/post")
    public ResponseEntity<?> findAllPostByStatusAndApply() {
        Users user = userService.getUsersPriciple();
        List<Post> postList = applyService.findAllPostByStatusAndApply(user.getId());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

//    @GetMapping("/list/post")
//    public ResponseEntity<?> findAllPostByStatusAndApply() {
//        Users user = userService.getUsersPriciple();
//        List<Long> idList =  applyService.findAllPostByStatusAndApply(user.getId());
//        List<Users> users = new ArrayList<>();
//        for (int i = 0; i < idList.size(); i++) {
//            users.add(userService.findById(idList.get(i)));
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

}

package com.codegym.findJob.controller;
import com.codegym.findJob.model.Post;
import com.codegym.findJob.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PostController {

    IPostService postService;

    @GetMapping("/findAllPost")
    public ResponseEntity<?> getAllPost(@PageableDefault Pageable pageable) {
        Page<Post> postPage = postService.findAllPosts(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findPostById(id), HttpStatus.OK);
    }

}

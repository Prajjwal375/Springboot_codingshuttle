package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.controllers;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Without it, Spring doesn't know this class should handle HTTP requests.
@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {
    // @RequestBody
    //It's not in the URL.
    //It's inside the request body.
//Now suppose you want to create a new employee.
    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    //We use @PathVariable to read a value from the URL path.
    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    @PutMapping("{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId ) {
        return postService.updatePost(inputPost, postId);
    }


}

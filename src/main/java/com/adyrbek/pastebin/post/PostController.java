package com.adyrbek.pastebin.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles/{profileId}")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@PathVariable String profileId, @RequestBody Post post) {
        postService.createPost(profileId, post);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable String profileId, @PathVariable String postId) {
        Post post = postService.getPost(profileId, postId);
        if (post != null) return new ResponseEntity<>(post, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String profileId, @PathVariable String postId, @RequestBody Post updatedPost) {
        if (postService.updatePost(profileId, postId, updatedPost)) return new ResponseEntity<>("Post updated", HttpStatus.OK);
        else return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String profileId, @PathVariable String postId) {
        if (postService.deletePost(profileId, postId)) return new ResponseEntity<>("Post deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable String profileId) {
        return new ResponseEntity<>(postService.getAllPosts(profileId), HttpStatus.OK);
    }
}
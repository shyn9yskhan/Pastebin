package com.adyrbek.post_service.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestParam String profileId, @RequestBody Post post) {
        postService.createPost(profileId, post);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@RequestParam String profileId, @PathVariable String postId) {
        Post post = postService.getPost(profileId, postId);
        if (post != null) return new ResponseEntity<>(post, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@RequestParam String profileId, @PathVariable String postId, @RequestBody Post updatedPost) {
        if (postService.updatePost(profileId, postId, updatedPost)) return new ResponseEntity<>("Post updated", HttpStatus.OK);
        else return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@RequestParam String profileId, @PathVariable String postId) {
        if (postService.deletePost(profileId, postId)) return new ResponseEntity<>("Post deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam String profileId) {
        return new ResponseEntity<>(postService.getAllPosts(profileId), HttpStatus.OK);
    }
}

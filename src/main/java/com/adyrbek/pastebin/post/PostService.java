package com.adyrbek.pastebin.post;

import java.util.List;

public interface PostService {
    boolean createPost(String profileId, Post post);
    Post getPost(String profileId, String postId);
    boolean updatePost(String profileId, String postId, Post updatedPost);
    boolean deletePost(String profileId, String postId);
    List<Post> getAllPosts(String profileId);
}
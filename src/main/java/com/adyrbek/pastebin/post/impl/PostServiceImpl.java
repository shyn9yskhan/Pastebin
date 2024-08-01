package com.adyrbek.pastebin.post.impl;

import com.adyrbek.pastebin.idgeneration.IdGenerationService;
import com.adyrbek.pastebin.post.Post;
import com.adyrbek.pastebin.post.PostRepository;
import com.adyrbek.pastebin.post.PostService;
import com.adyrbek.pastebin.profile.Profile;
import com.adyrbek.pastebin.profile.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    ProfileService profileService;
    IdGenerationService idGenerationService;

    public PostServiceImpl(PostRepository postRepository, ProfileService profileService, IdGenerationService idGenerationService) {
        this.postRepository = postRepository;
        this.profileService = profileService;
        this.idGenerationService = idGenerationService;
    }

    @Override
    public boolean createPost(String profileId, Post post) {
        Profile profile = profileService.getProfile(profileId);
        if (profile != null) {
            post.setId(idGenerationService.generatePostId());
            post.setProfile(profile);
            postRepository.save(post);
            return true;
        }
        else return false;
    }

    @Override
    public Post getPost(String profileId, String postId) {
        List<Post> posts = getAllPosts(profileId);
        return posts.stream().filter(post -> post.getId().equals(postId)).findFirst().orElse(null);
    }

    @Override
    public boolean updatePost(String profileId, String postId, Post updatedPost) {
        Post post = getPost(profileId, postId);
        if (post != null) {
            updatedPost.setId(post.getId());
            updatedPost.setProfile(post.getProfile());
            postRepository.save(updatedPost);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deletePost(String profileId, String postId) {
        if (profileService.getProfile(profileId) != null && postRepository.existsById(postId)) {
            Post post = postRepository.findById(postId).orElse(null);
            Profile profile = post.getProfile();
            profile.getPosts().remove(post);
            post.setProfile(null);
            profileService.updateProfile(profileId, profile);
            postRepository.deleteById(postId);
            return true;
        }
        else return false;
    }

    @Override
    public List<Post> getAllPosts(String profileId) {
        return postRepository.findByProfileId(profileId);
    }
}
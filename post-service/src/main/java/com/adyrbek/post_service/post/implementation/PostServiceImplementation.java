package com.adyrbek.post_service.post.implementation;

import com.adyrbek.post_service.post.Post;
import com.adyrbek.post_service.post.PostRepository;
import com.adyrbek.post_service.post.PostService;
import org.springframework.stereotype.Service;
import com.adyrbek.post_service.post.external.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService {

    PostRepository postRepository;

    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean createPost(String profileId, Post post) {
        RestTemplate restTemplate = new RestTemplate();
        String generatedPostId = restTemplate.getForObject("http://localhost:8081/idGeneration/postId", String.class);
        Profile profile = getProfileFromProfileService(profileId);

        if (profile != null) {
            post.setId(generatedPostId);
            post.setProfileId(profile.getId());
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
            updatedPost.setProfileId(post.getProfileId());
            postRepository.save(updatedPost);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deletePost(String profileId, String postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return true;
        }
        else return false;
    }

    @Override
    public List<Post> getAllPosts(String profileId) {
        return postRepository.findByProfileId(profileId);
    }

    private Profile getProfileFromProfileService(String profileId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8083/profiles/" + profileId, Profile.class);
    }
}

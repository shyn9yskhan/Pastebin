package com.adyrbek.pastebin.profile;

import com.adyrbek.pastebin.post.Post;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Profile {
    @Id
    private String id;
    private String username;

    @OneToMany(mappedBy = "profile")
    private List<Post> posts;

    public Profile() {}

    public Profile(String id, String username, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.posts = posts;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public List<Post> getPosts() { return posts; }

    public void setPosts(List<Post> posts) { this.posts = posts; }
}
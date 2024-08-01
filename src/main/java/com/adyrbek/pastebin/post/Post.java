package com.adyrbek.pastebin.post;

import com.adyrbek.pastebin.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    private String id;
    private String content;

    @JsonIgnore
    @ManyToOne
    private Profile profile;

    public Post() {}

    public Post(String id, String content, Profile profile) {
        this.id = id;
        this.content = content;
        this.profile = profile;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }
}
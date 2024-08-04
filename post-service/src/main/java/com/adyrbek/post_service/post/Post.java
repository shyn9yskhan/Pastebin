package com.adyrbek.post_service.post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    private String id;
    private String content;

    private String profileId;

    public Post() {}

    public Post(String id, String content, String profileId) {
        this.id = id;
        this.content = content;
        this.profileId = profileId;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getProfileId() { return profileId; }

    public void setProfileId(String profileId) { this.profileId = profileId; }
}

package com.adyrbek.post_service.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.adyrbek.post_service.post.external.Profile;

@FeignClient(name = "PROFILE-SERVICE")
public interface ProfileClient {

    @GetMapping("/profiles/{profileId}")
    ResponseEntity<Profile> getProfile(@PathVariable String profileId);
}
package com.adyrbek.profile_service.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ID-GENERATION-SERVICE")
public interface IdGenerationClient {

    @GetMapping("/idGeneration/profileId")
    ResponseEntity<String> generatePostId();
}

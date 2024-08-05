package com.adyrbek.post_service.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ID-GENERATION-SERVICE")
public interface IdGenerationClient {

    @GetMapping("/idGeneration/postId")
    ResponseEntity<String> generatePostId();
}
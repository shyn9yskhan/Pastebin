package com.adyrbek.id_generation_service.id_generation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idGeneration")
public class IdGenerationController {

    IdGenerationService idGenerationService;

    public IdGenerationController(IdGenerationService idGenerationService) {
        this.idGenerationService = idGenerationService;
    }

    @GetMapping("/profileId")
    public ResponseEntity<String> generateProfileId() {
        return new ResponseEntity<>(idGenerationService.generateProfileId(), HttpStatus.OK);
    }

    @GetMapping("/postId")
    public ResponseEntity<String> generatePostId() {
        return new ResponseEntity<>(idGenerationService.generatePostId(), HttpStatus.OK);
    }
}

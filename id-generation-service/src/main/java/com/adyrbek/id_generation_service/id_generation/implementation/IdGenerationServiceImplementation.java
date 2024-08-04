package com.adyrbek.id_generation_service.id_generation.implementation;

import com.adyrbek.id_generation_service.id_generation.IdGenerationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerationServiceImplementation implements IdGenerationService {
    @Override
    public String generateProfileId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generatePostId() {
        return UUID.randomUUID().toString();
    }
}

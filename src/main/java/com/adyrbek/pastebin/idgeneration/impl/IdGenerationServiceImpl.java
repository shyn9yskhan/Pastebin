package com.adyrbek.pastebin.idgeneration.impl;

import com.adyrbek.pastebin.idgeneration.IdGenerationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerationServiceImpl implements IdGenerationService {
    @Override
    public String generateProfileId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generatePostId() {
        return UUID.randomUUID().toString();
    }
}
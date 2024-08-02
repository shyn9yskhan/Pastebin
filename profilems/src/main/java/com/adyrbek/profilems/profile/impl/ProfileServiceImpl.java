package com.adyrbek.profilems.profile.impl;

import com.adyrbek.profilems.profile.Profile;
import com.adyrbek.profilems.profile.ProfileRepository;
import com.adyrbek.profilems.profile.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void createProfile(Profile profile) {
        RestTemplate restTemplate = new RestTemplate();
        String generatedProfileId = restTemplate.getForObject("http://localhost:8081/idGeneration/profileId", String.class);
        profile.setId(generatedProfileId);
        profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(String id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateProfile(String id, Profile updatedProfile) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();

            profile.setUsername(updatedProfile.getUsername());

            profileRepository.save(profile);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProfile(String id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}

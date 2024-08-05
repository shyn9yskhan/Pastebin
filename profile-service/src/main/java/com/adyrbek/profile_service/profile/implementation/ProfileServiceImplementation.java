package com.adyrbek.profile_service.profile.implementation;

import com.adyrbek.profile_service.profile.ProfileService;
import com.adyrbek.profile_service.profile.Profile;
import com.adyrbek.profile_service.profile.ProfileRepository;
import com.adyrbek.profile_service.profile.client.IdGenerationClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProfileServiceImplementation implements ProfileService {
    ProfileRepository profileRepository;
    IdGenerationClient idGenerationClient;

    public ProfileServiceImplementation(ProfileRepository profileRepository, IdGenerationClient idGenerationClient) {
        this.profileRepository = profileRepository;
        this.idGenerationClient = idGenerationClient;
    }

    @Override
    public void createProfile(Profile profile) {
        String generatedProfileId = idGenerationClient.generatePostId().getBody();
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

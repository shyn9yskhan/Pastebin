package com.adyrbek.pastebin.profile.impl;

import com.adyrbek.pastebin.idgeneration.IdGenerationService;
import com.adyrbek.pastebin.profile.Profile;
import com.adyrbek.pastebin.profile.ProfileRepository;
import com.adyrbek.pastebin.profile.ProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileRepository;
    IdGenerationService idGenerationService;

    public ProfileServiceImpl(ProfileRepository profileRepository, IdGenerationService idGenerationService) {
        this.profileRepository = profileRepository;
        this.idGenerationService = idGenerationService;
    }

    @Override
    public void createProfile(Profile profile) {
        profile.setId(idGenerationService.generateProfileId());
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
            profile.setPosts(updatedProfile.getPosts());

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

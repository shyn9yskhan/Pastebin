package com.adyrbek.profilems.profile;

public interface ProfileService {
    void createProfile(Profile profile);
    Profile getProfile(String id);
    boolean updateProfile(String id, Profile updatedProfile);
    boolean deleteProfile(String id);
}
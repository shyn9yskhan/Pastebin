package com.adyrbek.pastebin.profile;

public interface ProfileService {
    void createProfile(Profile profile);
    Profile getProfile(String id);
    boolean updateProfile(String id, Profile updatedProfile);
    boolean deleteProfile(String id);
}
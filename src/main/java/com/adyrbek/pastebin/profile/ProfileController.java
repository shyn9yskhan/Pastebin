package com.adyrbek.pastebin.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody Profile profile) {
        profileService.createProfile(profile);
        return new ResponseEntity<>("Profile created", HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable String profileId) {
        Profile profile = profileService.getProfile(profileId);
        if (profile != null) return new ResponseEntity<>(profile, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<String> updateProfile(@PathVariable String profileId, @RequestBody Profile updatedProfile) {
        if (profileService.updateProfile(profileId, updatedProfile)) return new ResponseEntity<>("Profile updated", HttpStatus.OK);
        else return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable String profileId) {
        boolean isDeleted = profileService.deleteProfile(profileId);
        if (isDeleted) return new ResponseEntity<>("Profile deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
    }
}
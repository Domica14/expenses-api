package service;

import java.util.UUID;

import dto.request.RequestProfileDto;
import dto.request.RequestUpdateProfile;
import dto.response.ResponseProfileDto;
import entity.Profile;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.ProfileMapper;
import repository.ProfileRepository;
import repository.UserRepository;

@ApplicationScoped
public class ProfileService {
    
    @Inject
    ProfileRepository profileRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    ProfileMapper profileMapper;

    @Inject
    JwtService jwtService;

    public ResponseProfileDto getUserProfile() {
        UUID userId = jwtService.getUserId();
        Profile profile = this.profileRepository.getByUserId(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
        return this.profileMapper.toDto(profile);
    }

    @Transactional
    public ResponseProfileDto createUserProfile(RequestProfileDto request) {
        UUID userId = jwtService.getUserId();
        User user = this.userRepository.findById(userId);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        Profile profile = this.profileMapper.toEntity(request, user);
        Profile profileCreated = this.profileRepository.save(profile);
        return this.profileMapper.toDto(profileCreated);
    }

    @Transactional
    public ResponseProfileDto updateUserProfile(RequestUpdateProfile request) {
        UUID userId = this.jwtService.getUserId();
        Profile profile = this.profileRepository.getByUserId(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.name = request.name;
        profile.lastname = request.lastName;
        profile.phoneNumber = request.phoneNumber;
        return this.profileMapper.toDto(profile);
    }

    @Transactional
    public void deleteUserProfile() {
        UUID userId = this.jwtService.getUserId();
        long deleted = this.profileRepository.delete("user.id", userId);
        if(deleted == 0){
            throw new RuntimeException("Profile not found");
        }
    }
}

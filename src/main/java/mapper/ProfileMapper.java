package mapper;

import dto.request.RequestProfileDto;
import dto.response.ResponseProfileDto;
import entity.Profile;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileMapper {
    
    public Profile toEntity(RequestProfileDto request, User user) {
        Profile profile = new Profile();
        profile.name = request.name;
        profile.lastname = request.lastName;
        profile.phoneNumber = request.phoneNumber;
        profile.user = user;
        return profile;
    }

    public ResponseProfileDto toDto(Profile profile) {
        ResponseProfileDto response = new ResponseProfileDto();
        response.id = profile.id;
        response.name = profile.name;
        response.lastname = profile.lastname;
        response.phoneNumber = profile.phoneNumber;
        return response;
    }
}

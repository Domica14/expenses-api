package resource;

import dto.request.RequestProfileDto;
import dto.request.RequestUpdateProfile;
import dto.response.ResponseProfileDto;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import service.ProfileService;

@Path("/profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ProfileResource {

    @Inject
    ProfileService profileService;

    @GET
    @Path("/me")
    public ResponseProfileDto getProfile() {
        return this.profileService.getUserProfile();
    }

    @POST
    @Path("/")
    public ResponseProfileDto createProfile(@Valid RequestProfileDto request) {
        return this.profileService.createUserProfile(request);
    }

    @PATCH
    @Path("/me")
    public ResponseProfileDto updateProfile(@Valid RequestUpdateProfile request) {
        return this.profileService.updateUserProfile(request);
    }

    @DELETE
    @Path("/me")
    public void deleteProfile() {
        this.profileService.deleteUserProfile();
    }
    
}

package resource;

import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import dto.user.UserRequest;
import dto.user.UserResponse;
import entity.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mapper.UserMapper;
import service.UserService;

@SecurityRequirement(name = "jwt")
@RolesAllowed("user")
@Path("/api/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)   
public class UserResource {
    
    @Inject
    UserService userService;
    @Inject
    UserMapper userMapper;

    @POST
    public UserResponse create(@Valid UserRequest request) {
        User user = this.userMapper.toEntity(request);
        User userCreated = this.userService.createUser(user);
        return this.userMapper.toResponse(userCreated);
    }
}

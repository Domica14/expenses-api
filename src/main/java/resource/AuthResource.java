package resource;

import dto.auth.LoginRequest;
import dto.auth.LoginResponse;
import entity.User;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mapper.LoginMapper;
import service.JwtService;
import service.UserService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    UserService userService;

    @Inject
    LoginMapper loginMapper;

    @Inject
    JwtService jwtService;

    @POST
    @Path("/login")
    public LoginResponse login(@Valid LoginRequest request) {
        User user = this.userService.login(this.loginMapper.toEntity(request));
        String token = this.jwtService.generateToken(user.id.toString());
        return this.loginMapper.toResponse(user, token);
    }
}

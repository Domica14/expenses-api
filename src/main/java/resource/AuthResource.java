package resource;

import dto.request.RequestAuthDto;
import dto.response.ResponseLoginDto;
import dto.response.ResponseSignUpDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import service.AuthService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    
    @Inject
    AuthService authService;

    @POST
    @Path("/signup")
    public ResponseSignUpDto signUp(@Valid RequestAuthDto request) {
        return this.authService.createUser(request);
    }

    @POST
    @Path("/login")
    public ResponseLoginDto login(@Valid RequestAuthDto request) {
        return this.authService.verifyUser(request);
    }

}

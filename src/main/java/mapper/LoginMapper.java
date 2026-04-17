package mapper;

import dto.auth.LoginRequest;
import dto.auth.LoginResponse;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginMapper {
    
    public User toEntity(LoginRequest request) {
        User user = new User();
        user.email = request.email;
        user.password = request.password;
        return user;
    }

    public LoginResponse toResponse(User user, String token) {
        LoginResponse response = new LoginResponse();
        response.id = user.id;
        response.email = user.email;
        response.token = token;
        return response;
    }
}

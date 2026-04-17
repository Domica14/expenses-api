package mapper;

import dto.user.UserRequest;
import dto.user.UserResponse;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {
    
    public User toEntity(UserRequest request) {
        User user = new User();
        user.email = request.email;
        user.password = request.password;
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.id = user.id;
        response.email = user.email;
        return response;
    }
}

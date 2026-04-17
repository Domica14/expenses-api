package mapper;

import dto.request.RequestAuthDto;
import dto.response.ResponseLoginDto;
import dto.response.ResponseSignUpDto;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthMapper {
    
    public User toEntity(RequestAuthDto request) {
        User user = new User();
        user.email = request.email;
        user.password = request.password;
        return user;
    }

    public ResponseSignUpDto toSignUpDto(User user) {
        ResponseSignUpDto response = new ResponseSignUpDto();
        response.id = user.id;
        response.email = user.email;
        return response;
    }

    public ResponseLoginDto toLoginDto(User user, String token) {
        ResponseLoginDto response = new ResponseLoginDto();
        response.id = user.id;
        response.email = user.email;
        response.token = token;

        return response;
    }
}

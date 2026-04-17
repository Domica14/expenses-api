package service;

import dto.request.RequestAuthDto;
import dto.response.ResponseLoginDto;
import dto.response.ResponseSignUpDto;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.AuthMapper;
import repository.UserRepository;

@ApplicationScoped
public class AuthService {
    
    @Inject
    UserRepository userRepository;

    @Inject
    AuthMapper AuthMapper;

    @Inject
    PasswordService passwordService;

    @Inject
    JwtService jwtService;

    @Transactional
    public ResponseSignUpDto createUser(RequestAuthDto request) {
        User user = this.AuthMapper.toEntity(request);
        user.password = this.passwordService.hashPassword(request.password);
        User userCreated = this.userRepository.save(user);
        return this.AuthMapper.toSignUpDto(userCreated);
    }

    public ResponseLoginDto verifyUser(RequestAuthDto request) {
        User user = this.userRepository.findByEmail(request.email).orElseThrow(() -> new RuntimeException("User not found"));
        boolean validated = this.passwordService.verifyPassword(user.password, request.password);
        if (!validated) {
            throw new RuntimeException("Invalid credential");
        }
        String token = jwtService.generateToken(user);
        return this.AuthMapper.toLoginDto(user, token);
    }
}

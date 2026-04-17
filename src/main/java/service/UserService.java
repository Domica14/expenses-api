package service;

import entity.User;
import exception.ApiException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repository.UserRepository;

@ApplicationScoped
public class UserService {
    
    @Inject
    UserRepository userRepository;

    @Inject
    PasswordService passwordService;

    @Transactional
    public User createUser(User user) {
        user.password = this.passwordService.hashPassword(user.password);
        userRepository.persist(user);
        return user;
    }

    public User login(User user) {
        User dbUser = this.userRepository.FindByEmail(user.email).orElseThrow(() -> new ApiException("User not found", 404));
        boolean validPassword = this.passwordService.verifyPassword(user.password, dbUser.password);

        if (!validPassword) {
            throw new ApiException("Invalid credentials", 401);
        }

        return dbUser;
    }
}

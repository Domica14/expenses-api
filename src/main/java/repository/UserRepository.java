package repository;

import java.util.Optional;
import java.util.UUID;

import entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID> {

    public Optional<User> findByEmail(String email) {
        return this.find("email", email).firstResultOptional();
    }

    public User save(User user) {
        user.persist();
        return user;
    }
}

package repository;

import java.util.Optional;

import entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> FindByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}

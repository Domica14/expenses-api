package repository;

import java.util.Optional;
import java.util.UUID;

import entity.Profile;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileRepository implements PanacheRepositoryBase<Profile, UUID> {
    public Optional<Profile> getByUserId(UUID userId) {
        return this.find("user.id", userId).firstResultOptional();
    }

    public Profile save(Profile profile) {
        profile.persist();
        return profile;
    }
}

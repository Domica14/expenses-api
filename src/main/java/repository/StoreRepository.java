package repository;

import java.util.Optional;
import java.util.UUID;

import entity.Store;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreRepository implements PanacheRepositoryBase<Store, UUID> {

    public Optional<Store> getStoreById(UUID id) {
        return this.findByIdOptional(id);
    }
}

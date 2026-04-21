package repository;

import java.util.Optional;
import java.util.UUID;

import entity.Store;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreRepository implements PanacheRepositoryBase<Store, UUID> {

    public PanacheQuery<Store> findAll(String name, int page, int size) {
        if (name == null || name.isEmpty()) {
            return this.findAll().page(Page.of(page, size));
        }
        return this.find("LOWER(name) LIKE ?1", "%" + name.toLowerCase() + "%").page(Page.of(page, size));
    }

    public Optional<Store> getStoreById(UUID id) {
        return this.findByIdOptional(id);
    }

    public Store save(Store store) {
        store.persist();
        return store;
    }

    public void  delete(UUID id) {
        this.delete("id",  id);
    }
}

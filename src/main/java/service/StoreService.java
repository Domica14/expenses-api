package service;

import dto.request.RequestStoreDto;
import dto.response.ResponsePaginatedDto;
import dto.response.ResponseStoreDto;
import entity.Store;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.StoreMapper;
import repository.StoreRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StoreService {

    @Inject
    public StoreRepository storeRepository;

    @Inject
    public StoreMapper storeMapper;

    public ResponsePaginatedDto<ResponseStoreDto> getAll(String name, int page, int size) {
        size = Math.min(size, 100);
        page = Math.min(page, 0);

        PanacheQuery<Store> query = this.storeRepository.findAll(name, page, size);

        List<ResponseStoreDto> stores = query.list()
                .stream()
                .map(storeMapper::toResponse)
                .toList();
        long total = query.count();
        int totalPages = query.pageCount();

        return new ResponsePaginatedDto<>(stores, page, size, total, totalPages);
    }

    public ResponseStoreDto getStore(UUID id) {
        Store store = this.storeRepository.findById(id);
        if (store  == null) {
            throw new RuntimeException("Store with id " + id + " not found");
        }
        return this.storeMapper.toResponse(store);
    }

    @Transactional
    public ResponseStoreDto createStore(RequestStoreDto request) {
        Store store = this.storeMapper.toEntity(request);
        Store storeCreated = this.storeRepository.save(store);
        return this.storeMapper.toResponse(storeCreated);
    }

    @Transactional
    public ResponseStoreDto updateStore(UUID id, RequestStoreDto request) {
        Store store = this.storeRepository.findById(id);
        if (store == null) {
            throw new RuntimeException("Store with id " + id + " not found");
        }
        store.name = request.name;
        store.address = request.address;
        return this.storeMapper.toResponse(store);
    }

    @Transactional
    public void deleteStore(UUID id) {
        Store store = this.storeRepository.findById(id);
        if (store == null) {
            throw new RuntimeException("Store with id " + id + " not found");
        }
        this.storeRepository.delete(store.id);
    }
}
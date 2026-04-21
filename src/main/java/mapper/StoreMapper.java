package mapper;


import dto.request.RequestStoreDto;
import dto.response.ResponseStoreDto;
import entity.Store;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoreMapper {

    public Store toEntity(RequestStoreDto request) {
        Store store = new Store();
        store.name = request.name;
        store.address = request.address;
        return store;
    }

    public ResponseStoreDto toResponse(Store store) {
        ResponseStoreDto response = new ResponseStoreDto();
        response.id = store.id;
        response.name = store.name;
        response.address = store.address;
        return response;
    }
}

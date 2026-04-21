package resource;


import dto.request.RequestStoreDto;
import dto.response.ResponsePaginatedDto;
import dto.response.ResponseStoreDto;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import service.StoreService;

import java.util.UUID;

@Path("store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class StoreResource {

    @Inject
    StoreService storeService;

    @GET
    public ResponsePaginatedDto<ResponseStoreDto> getAll(
            @QueryParam("name") String name,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        return this.storeService.getAll(name, page, size);
    }

    @GET
    @Path("{id}")
    public ResponseStoreDto getById(@PathParam("id") UUID id) {
        return this.storeService.getStore(id);
    }

    @POST
    public ResponseStoreDto create(@Valid RequestStoreDto request) {
        return this.storeService.createStore(request);
    }

    @PUT
    @Path("{id}")
    public ResponseStoreDto update(@PathParam("id") UUID id, @Valid RequestStoreDto request) {
        return this.storeService.updateStore(id, request);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") UUID id) {
        this.storeService.deleteStore(id);
    }
}

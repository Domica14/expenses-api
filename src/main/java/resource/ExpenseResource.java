package resource;

import dto.request.RequestExpenseDto;
import dto.response.ResponseExpenseDto;
import dto.response.ResponsePaginatedDto;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import service.ExpenseService;

import java.util.UUID;

@Path("/expense")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExpenseResource {

    @Inject
    ExpenseService expenseService;

    @GET
    public ResponsePaginatedDto<ResponseExpenseDto> getUserAll(
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("page") @DefaultValue("0") int page) {
        return this.expenseService.getAllExpenses(page, size);
    }

    @GET
    @Path("/{id}")
    public ResponseExpenseDto getById(@PathParam("id") UUID id) {
        return this.expenseService.getExpenseById(id);
    }

    @POST
    public ResponseExpenseDto create(@Valid RequestExpenseDto request) {
        return this.expenseService.createExpense(request);
    }

    @PUT
    @Path("/{id}")
    public ResponseExpenseDto update(@Valid RequestExpenseDto request, @PathParam("id") UUID id) {
        return this.expenseService.updateExpense(request, id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        this.expenseService.deleteExpense(id);
    }
}

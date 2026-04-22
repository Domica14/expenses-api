package mapper;


import dto.request.RequestExpenseDto;
import dto.response.ResponseExpenseDto;
import dto.response.ResponseStoreDto;
import entity.Expense;
import entity.Store;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseMapper {

    public Expense toEntity(RequestExpenseDto request, Store store, User user) {
        Expense expense = new Expense();
        expense.amount =  request.amount;
        expense.date =  request.date;
        expense.category = request.category;
        expense.description = request.description;
        expense.store = store;
        expense.user = user;
        return expense;
    }

    public ResponseExpenseDto toDtoResponse(Expense expense) {
        ResponseExpenseDto responseExpenseDto = new ResponseExpenseDto();
        responseExpenseDto.id = expense.id;
        responseExpenseDto.amount = expense.amount;
        responseExpenseDto.description = expense.description;
        responseExpenseDto.category = expense.category;
        responseExpenseDto.date = expense.date;
        responseExpenseDto.store = new ResponseStoreDto();
        responseExpenseDto.store.id = expense.store.id;
        responseExpenseDto.store.name = expense.store.name;
        responseExpenseDto.store.address = expense.store.address;
        return responseExpenseDto;
    }
}

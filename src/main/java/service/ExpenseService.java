package service;


import dto.request.RequestExpenseDto;
import dto.response.ResponseExpenseDto;
import dto.response.ResponsePaginatedDto;
import entity.Expense;
import entity.Store;
import entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.ExpenseMapper;
import repository.ExpensesRepository;
import repository.StoreRepository;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ExpenseService {

    @Inject ExpensesRepository expensesRepository;

    @Inject ExpenseMapper expenseMapper;

    @Inject StoreRepository storeRepository;

    @Inject UserRepository userRepository;

    @Inject JwtService jwtService;

    public ResponsePaginatedDto<ResponseExpenseDto> getAllExpenses(int page, int size) {
        size = Math.min(size, 10);
        page = Math.min(page, 0);

        UUID userId = jwtService.getUserId();

        PanacheQuery<Expense> query = this.expensesRepository.getAllUserExpenses(userId, page, size);

        List<ResponseExpenseDto> expenses = query.list()
                .stream()
                .map(expenseMapper::toDtoResponse)
                .toList();
        long total = query.count();
        int totalPages = query.pageCount();

        return new ResponsePaginatedDto<>(expenses, page, size, total, totalPages);
    }

    public ResponseExpenseDto getExpenseById(UUID id) {
        Expense expense = this.expensesRepository.getExpenseById(id).orElseThrow(
                () -> new RuntimeException("Expense with id: " + id + " not found")
        );
        return this.expenseMapper.toDtoResponse(expense);
    }

    @Transactional
    public  ResponseExpenseDto createExpense(RequestExpenseDto requestExpenseDto) {
        User user = this.userRepository.findById(
                this.jwtService.getUserId()
        );
        if  (user == null) {
            throw new RuntimeException("User not found");
        }
        Store store = this.storeRepository.findById(requestExpenseDto.storeId);
        if  (store == null) {
            throw new RuntimeException("Store with id: " + requestExpenseDto.storeId + " not found");
        }
        Expense expense = this.expensesRepository.save(
                this.expenseMapper.toEntity(requestExpenseDto, store, user)
        );
        return this.expenseMapper.toDtoResponse(expense);
    }

    @Transactional
    public  ResponseExpenseDto updateExpense(RequestExpenseDto requestExpenseDto, UUID id) {
        Expense expense = this.expensesRepository.getExpenseById(id).orElseThrow(
                () -> new RuntimeException("Expense with id: " + id + " not found")
        );
        Store store = this.storeRepository.findById(requestExpenseDto.storeId);
        if  (store == null) {
            throw new RuntimeException("Store with id: " + requestExpenseDto.storeId + " not found");
        }
        expense.description = requestExpenseDto.description;
        expense.category = requestExpenseDto.category;
        expense.amount = requestExpenseDto.amount;
        expense.date = requestExpenseDto.date;
        expense.store = store;
        return  this.expenseMapper.toDtoResponse(expense);
    }

    @Transactional
    public void deleteExpense(UUID id) {
        Expense expense = this.expensesRepository.getExpenseById(id).orElseThrow(
                () -> new RuntimeException("Expense with id: " + id + " not found")
        );
        this.expensesRepository.remove(expense.id);
    }
}

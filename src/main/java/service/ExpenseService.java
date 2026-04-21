package service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.ExpensesRepository;

@ApplicationScoped
public class ExpenseService {

    @Inject
    ExpensesRepository expensesRepository;
}

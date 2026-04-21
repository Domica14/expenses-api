package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import entity.Expense;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpensesRepository implements PanacheRepositoryBase<Expense, UUID> {
    
    public PanacheQuery<Expense> getAllUserExpenses(UUID userId, int page, int pageSize) {
        return this.find("user.id = ?0", userId).page(Page.of(page, pageSize));
    }

    public Optional<Expense> getExpenseById(UUID id) {
        return this.findByIdOptional(id);
    }

    public Expense save(Expense expense) {
        expense.persist();
        return expense;
    }

    public void remove(UUID id) {
        this.delete("id = ?0", id);
    }
}

package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import entity.Expense;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpensesRepository implements PanacheRepositoryBase<Expense, UUID> {
    
    public List<Expense> getExpenseByUserid(UUID userId) {
        return this.find("user.id", userId).list();
    }

    public Optional<Expense> getExpenseById(UUID id) {
        return this.findByIdOptional(id);
    }
}

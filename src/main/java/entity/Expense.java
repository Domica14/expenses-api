package entity;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public Double amount;

    @Column(nullable = false)
    public String category;

    @Column(nullable = false)
    public String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    public Store store;
}

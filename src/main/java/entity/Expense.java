package entity;

import java.time.Instant;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Enumerated(EnumType.STRING)
    public Categories category;

    public Instant date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    public Store store;
}

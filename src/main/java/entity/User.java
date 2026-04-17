package entity;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    public UUID id;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(nullable = false)
    public String password;
}

package dto.request;

import entity.Categories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

public class RequestExpenseDto {

    @NotBlank
    @Length(max = 255)
    public String description;

    @NotNull
    public Double amount;

    @NotNull
    public Categories category;

    @PastOrPresent
    public Instant date;

    public UUID storeId;
}

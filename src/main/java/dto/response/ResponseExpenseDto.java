package dto.response;

import entity.Categories;

import java.time.Instant;
import java.util.UUID;

public class ResponseExpenseDto {

    public UUID id;
    public String description;
    public Double amount;
    public Categories category;
    public Instant date;
    public ResponseStoreDto store;
}

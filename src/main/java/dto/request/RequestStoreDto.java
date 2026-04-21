package dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class RequestStoreDto {

    @NotBlank
    @Length(max = 50)
    public String name;

    @NotBlank
    @Length(max = 255)
    public String address;
}
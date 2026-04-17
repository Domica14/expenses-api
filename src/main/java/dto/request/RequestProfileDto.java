package dto.request;

import jakarta.validation.constraints.NotBlank;

public class RequestProfileDto {
    
    @NotBlank
    public String name;

    @NotBlank
    public String lastName;

    @NotBlank
    public String phoneNumber;
}

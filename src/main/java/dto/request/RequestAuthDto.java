package dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequestAuthDto {
    
    @Email
    @NotBlank
    public String email;

    @NotBlank
    @Length(min = 8, max = 16, message = "Password must be at least 8 and no more than 16 characters")
    public String password;
}

package dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    
    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String password;
}

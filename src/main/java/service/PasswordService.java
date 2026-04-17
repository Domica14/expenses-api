package service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {
    
    public String hashPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }

    public boolean verifyPassword(String hashedPassword, String password) {
        return BcryptUtil.matches(password, hashedPassword);
    }
}

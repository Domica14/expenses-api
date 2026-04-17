package service;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import entity.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JwtService {
    
    @Inject
    JsonWebToken jwt;

    private static final String ISSUER = "expenses-app-v2";

    public String generateToken(User user) {
        return Jwt.issuer(ISSUER)
                .subject(user.id.toString())
                .upn(user.email)
                .claim("email", user.email)
                .expiresIn(3600)
                .sign();
    }

    public UUID getUserId() {
        return UUID.fromString(jwt.getSubject());
    }

    public String getEmail() {
        return jwt.getClaim("email");
    }
}

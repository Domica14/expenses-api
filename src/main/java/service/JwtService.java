package service;

import java.time.Duration;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {

    public String generateToken(String id) {
        return Jwt.issuer("expenses-api")
                .subject(id)
                .groups(Set.of("user"))
                .expiresIn(Duration.ofHours(2))
                .sign();
    }
}

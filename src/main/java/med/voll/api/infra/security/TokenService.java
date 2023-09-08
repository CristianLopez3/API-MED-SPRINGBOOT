package med.voll.api.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;



/**
 * Esta clase es la que nos va ayudar a generar los jwt(json web tokens)
 */
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String jwtSecret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(user.getLogin()) // esto deberia ser generado dinamicamente
                    .withExpiresAt(generateExpiratedDate())
                    .withClaim("id", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generateExpiratedDate(){
        return LocalDate.now().plusDays(1).atStartOfDay().toInstant(ZoneOffset.of("-05:00"));
    }

}

package br.com.alura.Forum.seguranca;

import br.com.alura.Forum.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${forum.Hub.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        System.out.println(secret);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpirar())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar tokem", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTCreationException exception) {
            throw new RuntimeException("inválido ou expirado");        }
    }


    private Instant dataExpirar() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

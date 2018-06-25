package quem.me.ajuda.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import quem.me.ajuda.exceptions.UserNotFoundException;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.UserCredentials;

@Service
@Transactional
public class AuthenticationService {
    private static final String ISSUER = "random";
    private final String secretKey = "random";
    
    @Autowired
    private StudentService studentService;

    public Optional<Student> authenticate(UserCredentials credentials) {
        Optional<Student> user = studentService.getByRegistration(credentials.getRegistration());

        if (user.isPresent() && BCrypt.checkpw(credentials.getPassword(), user.get().getPassword()))
            return user;

        return null;
    }

    public String tokenFor(Student user) throws IOException, URISyntaxException {

        Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(user.getRegistration())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Optional<Student> getUserFromToken(String token) throws IOException, URISyntaxException, UserNotFoundException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        Optional<Student> user = studentService.getByRegistration(claims.getBody().getSubject().toString());

        if (user.isPresent())
            return user;
        else
            throw new UserNotFoundException();
    }

}
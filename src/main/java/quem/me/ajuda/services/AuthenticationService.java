package quem.me.ajuda.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import quem.me.ajuda.exceptions.FailedAuthenticationException;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.UserCredentials;

@Service
public class AuthenticationService {
    private final String ISSUER = "random";
    private final String secretKey = "random";
    
    @Autowired
    private StudentService studentService;

    public Student authenticate(UserCredentials credentials) {
        Student user = studentService.getByRegistration(credentials.getRegistration());
        
        if (BCrypt.checkpw(credentials.getPassword(), user.getPassword()))
        	return user;
        
        throw new FailedAuthenticationException();
    }

    public String tokenFor(Student user) {
    	Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(user.getRegistration())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Student getUserFromToken(String token) {
         Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
         return  studentService.getByRegistration(claims.getBody().getSubject().toString());
    }

}
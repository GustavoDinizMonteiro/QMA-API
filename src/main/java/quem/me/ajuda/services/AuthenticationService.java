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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import quem.me.ajuda.exceptions.FailedAuthenticationException;
import quem.me.ajuda.exceptions.UserNotFoundException;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.UserCredentials;

@Service
public class AuthenticationService {
    private final String ISSUER = "random";
    private final String secretKey = "random";
    
    @Autowired
    private StudentService studentService;

    public Student authenticate(UserCredentials credentials) {
        Optional<Student> students = studentService.getByRegistration(credentials.getRegistration());
        
        return students.map(user -> {
        	if (BCrypt.checkpw(credentials.getPassword(), user.getPassword()))
        		return user;

        	throw new FailedAuthenticationException();
        }).orElseThrow(() -> new UserNotFoundException());
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

    public Student getUserFromToken(String token) throws IOException, URISyntaxException {
         Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
         Optional<Student> user = studentService.getByRegistration(claims.getBody().getSubject().toString());
         
         return user
        		 .map(student -> student)
        		 .orElseThrow(() -> new UserNotFoundException());
    }

}
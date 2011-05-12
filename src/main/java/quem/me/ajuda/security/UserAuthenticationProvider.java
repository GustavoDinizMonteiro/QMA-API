package quem.me.ajuda.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import quem.me.ajuda.exceptions.FailedAuthenticationException;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.AuthenticatedUser;
import quem.me.ajuda.services.AuthenticationService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	@Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Optional<Student> possibleProfile = authenticationService.getUserFromToken((String) authentication.getCredentials());
            
            return new AuthenticatedUser((String) authentication.getCredentials(), possibleProfile.get());
        } catch (Exception e) {
            throw new FailedAuthenticationException("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (AuthenticatedUser.class.isAssignableFrom(authentication));
    }
}
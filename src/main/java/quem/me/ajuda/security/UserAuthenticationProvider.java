package quem.me.ajuda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
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
    public Authentication authenticate(Authentication authentication) {
        try {
            Student student = authenticationService.getUserFromToken((String) authentication.getCredentials());
            
            return new AuthenticatedUser((String) authentication.getCredentials(), student);
        } catch (Exception e) {
            throw new FailedAuthenticationException("Authentication failed: ".concat(e.getMessage()));
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (AuthenticatedUser.class.isAssignableFrom(authentication));
    }
}
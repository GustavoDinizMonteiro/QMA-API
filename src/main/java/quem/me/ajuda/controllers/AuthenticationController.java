package quem.me.ajuda.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import quem.me.ajuda.exceptions.FailedAuthenticationException;
import quem.me.ajuda.security.model.AuthenticatedUser;
import quem.me.ajuda.security.model.UserCredentials;
import quem.me.ajuda.services.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping(path = "auth/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST)
    public AuthenticatedUser login(@RequestBody UserCredentials credentials, HttpServletResponse response) throws FailedAuthenticationException {
        return authenticationService.authenticate(credentials)
                .map(user -> {
                    String token;

                    try {
                        token = authenticationService.tokenFor(user);
                        response.setHeader("Authorization", token);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return new AuthenticatedUser(token, user);
                })
                .orElseThrow(() -> new FailedAuthenticationException(credentials.getRegistration()));
    }
}
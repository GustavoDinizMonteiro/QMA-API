package quem.me.ajuda.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.AuthenticatedUser;
import quem.me.ajuda.security.model.UserCredentials;
import quem.me.ajuda.services.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping(path = Endpoints.LOGIN_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticatedUser> login(@Valid @RequestBody UserCredentials credentials) {
        Student user = service.authenticate(credentials);
        String token = this.service.tokenFor(user);

        return ResponseEntity.ok(new AuthenticatedUser(token, user));
    }
}
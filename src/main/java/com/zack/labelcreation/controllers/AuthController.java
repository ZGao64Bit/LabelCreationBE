package com.zack.labelcreation.controllers;

import com.zack.labelcreation.models.Token;
import com.zack.labelcreation.models.User;
import com.zack.labelcreation.models.UserRole;
import com.zack.labelcreation.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate/")
    public Token authenticateHost(@RequestBody User user) {
        return authService.authenticate(user, UserRole.ROLE_HOST);
    }
}

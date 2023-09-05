package com.ZoomCar.controller;

import com.ZoomCar.auth.AuthenticationRequest;
import com.ZoomCar.auth.AuthenticationResponse;
import com.ZoomCar.auth.RegisterRequest;
import com.ZoomCar.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private static final Logger logger= LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        logger.info("Endpoint '/register' called");

        // Log the request payload if needed
        logger.debug("Register Request: {}", request);

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        logger.info("Endpoint '/authenticate' called");

        // Log the request payload if needed
        logger.debug("Authentication Request: {}", request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

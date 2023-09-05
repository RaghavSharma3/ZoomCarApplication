package com.ZoomCar.service;

import com.ZoomCar.auth.AuthenticationRequest;
import com.ZoomCar.auth.AuthenticationResponse;
import com.ZoomCar.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    public ResponseEntity<?> register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}

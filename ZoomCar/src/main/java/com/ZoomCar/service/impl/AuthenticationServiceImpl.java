package com.ZoomCar.service.impl;

import com.ZoomCar.auth.AuthenticationRequest;
import com.ZoomCar.auth.AuthenticationResponse;
import com.ZoomCar.auth.RegisterRequest;
import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.security.JwtService;
import com.ZoomCar.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_USER)
                    .isBlocked("No")
                    .build();

            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder()
                    .token(jwt)
                    .build());
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email already exists!");
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        ); // user is authenticated

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (user.getIsBlocked().equals("No")) {
            var jwt = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwt)
                    .build();
        }
        return AuthenticationResponse.builder().token(null).build();
    }
}


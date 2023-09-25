package com.ZoomCar;

import com.ZoomCar.auth.AuthenticationRequest;
import com.ZoomCar.auth.RegisterRequest;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.security.JwtService;
import com.ZoomCar.service.impl.AuthenticationServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void registerEmailAlreadyExists()
    {
        RegisterRequest request=new RegisterRequest();
        request.setPassword("password");
        request.setEmail("xyz@gmail.com");
        User user=new User();
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Assertions.assertNotNull(authenticationService.register(request));

    }
    @Test
    public void register()
    {
        RegisterRequest request=new RegisterRequest();
        request.setPassword("password");
        request.setEmail("xyz@gmail.com");
        User user=new User();
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertNotNull(authenticationService.register(request));

    }
    @Test
    public void authenticate()
    {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        User user=new User();
        user.setIsBlocked("No");
        authenticationRequest.setEmail("xyz@gmail.com");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Assertions.assertNotNull(authenticationService.authenticate(authenticationRequest));
    }
    @Test
    public void authenticateUserBlocked()
    {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        User user=new User();
        user.setIsBlocked("Yes");
        authenticationRequest.setEmail("xyz@gmail.com");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Assertions.assertNotNull(authenticationService.authenticate(authenticationRequest));
    }
}

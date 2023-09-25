package com.ZoomCar;


import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Test
    public void changeRoleTohost()
    {
        User user=new User();
        user.setRole(Role.ROLE_USER);
        Mockito.when(userRepository.findById(99)).thenReturn(Optional.of(user));
       Assertions.assertNull(userService.changeRoleToHost(99));
    }
    @Test
    public void changeRoleTohostNull()
    {
        User user=new User();
        user.setRole(Role.ROLE_USER);
        Mockito.when(userRepository.findById(99)).thenReturn(Optional.empty());
        Assertions.assertNull(userService.changeRoleToHost(99));
    }
}

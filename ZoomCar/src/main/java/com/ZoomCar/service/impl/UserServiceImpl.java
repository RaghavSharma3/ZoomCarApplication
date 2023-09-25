package com.ZoomCar.service.impl;

import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    @Override
    public User changeRoleToHost(Integer userId)
    {
        Optional<User> userOptional= userRepository.findById(userId);
        if(userOptional.isPresent() && userOptional.get().getRole()==Role.ROLE_USER) {
            User user = userOptional.get();
            user.setRole(Role.ROLE_HOST);
            return userRepository.save(user);
        }
        else{
            return null;
        }
    }
}

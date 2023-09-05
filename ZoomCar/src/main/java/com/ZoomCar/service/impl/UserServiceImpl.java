package com.ZoomCar.service.impl;

import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    @Override
    public String changeRoleToHost(Integer userId)
    {
        Optional<User> userOptional= userRepository.findById(userId);
        if(userOptional.isPresent() && userOptional.get().getRole()==Role.ROLE_USER)
        {
            if(userOptional.get().getIsBlocked().equals("No")) {
                User user = userOptional.get();
                user.setRole(Role.ROLE_HOST);
                userRepository.save(user);
                return "User's Role Changed to Host Successfully";
            }
            else {
                return "User is Blocked, Contact Admin";
            }
        }
        else{
            return "User with provided UserId not found";
        }
    }
    @Override
    public String blockUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(user.getRole()==Role.ROLE_USER) {
            user.setIsBlocked("Yes");
            userRepository.save(user);
            return "User with ID " + userId + " has been blocked.";
        }
        else {
            return "UserId is invalid";
        }
    }

    @Override
    public String blockHost(Integer hostId) {
        User host = userRepository.findById(hostId)
                .orElseThrow(() -> new UsernameNotFoundException("Host not found"));
        if(host.getRole()==Role.ROLE_HOST) {
            host.setIsBlocked("Yes");
            userRepository.save(host);
            return "Host with ID " + hostId + " has been blocked.";
        }
        else {
            return "HostId is invalid";
        }
    }
    @Override
    public User getUserDetails(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

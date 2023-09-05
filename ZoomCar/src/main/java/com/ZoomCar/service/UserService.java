package com.ZoomCar.service;

import com.ZoomCar.entity.User;

public interface UserService {
    public String changeRoleToHost(Integer userId);
    public String blockUser(Integer userId);
    public String blockHost(Integer hostId);
    public User getUserDetails(Integer userId);
}

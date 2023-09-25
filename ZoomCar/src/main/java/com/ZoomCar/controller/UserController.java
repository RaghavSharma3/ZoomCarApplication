package com.ZoomCar.controller;

import com.ZoomCar.entity.User;
import com.ZoomCar.service.BookingService;
import com.ZoomCar.service.CarService;
import com.ZoomCar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    public final UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    @PutMapping("/becomeHost/{userId}")
    public ResponseEntity<User> becomeHost(@PathVariable Integer userId)
    {
        User user= userService.changeRoleToHost(userId);
        if(user!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

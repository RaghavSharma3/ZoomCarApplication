package com.ZoomCar.controller;

import com.ZoomCar.entity.User;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.BlockHostPayload;
import com.ZoomCar.payload.BlockUserPayload;
import com.ZoomCar.payload.UpdateCarPayload;
import com.ZoomCar.service.BookingService;
import com.ZoomCar.service.CarService;
import com.ZoomCar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    public final UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/becomeHost/{userId}")
    public ResponseEntity<String> becomeHost(@PathVariable Integer userId)
    {
        String response= userService.changeRoleToHost(userId);
        if(response.equals("User's Role Changed to Host Successfully"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else if(response.equals("User is Blocked, Contact Admin"))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/block-user")
    public ResponseEntity<String> blockUser(@RequestBody BlockUserPayload blockUserPayload) {
        String response= userService.blockUser(blockUserPayload.getUserId());
        if(response.equals("User not found"))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(response.equals("UserId is invalid")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/block-host")
    public ResponseEntity<String> blockHost(@RequestBody BlockHostPayload blockHostPayload) {
        String response= userService.blockHost(blockHostPayload.getHostId());
        if(response.equals("Host not found"))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(response.equals("HostId is invalid")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-details/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Integer userId) {
        User user = userService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_HOST')")
    @PostMapping("/addCar")
    public ResponseEntity<String> addCar(@RequestBody AddCarPayLoad addCarPayLoad){

        String response=carService.addCar(addCarPayLoad);
        if(response.equals("Car Added Successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        else if(response.equals("Host is Blocked, Contact Admin")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    };


    @PutMapping("/updateCar")
    @PreAuthorize("hasRole('ROLE_HOST')")
    public ResponseEntity<String> updateCar(@RequestBody UpdateCarPayload updateCarPayload){

        String response= carService.updateCar(updateCarPayload);
        if(response.equals("Car Details Updated"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

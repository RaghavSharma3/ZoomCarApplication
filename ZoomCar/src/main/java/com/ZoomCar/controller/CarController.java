package com.ZoomCar.controller;

import com.ZoomCar.entity.Car;
import com.ZoomCar.payload.GetCarsPayload;
import com.ZoomCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getCars")
    public ResponseEntity<List<Car>> getCars(@RequestBody GetCarsPayload getCarsPayload) {
        System.out.println(getCarsPayload.toString());
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCars(getCarsPayload));
    }
}

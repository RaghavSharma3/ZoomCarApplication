package com.ZoomCar.controller;

import com.ZoomCar.entity.Car;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;
import com.ZoomCar.service.CarService;
import com.ZoomCar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final UserService userService;

    @GetMapping("/getCars")
    public ResponseEntity<List<Car>> getCars(
            @RequestParam(name = "startDateTime", required = false) String startDateTime,
            @RequestParam(name = "endDateTime", required = false) String endDateTime
    ) {
        GetCarsPayload getCarsPayload= new GetCarsPayload(startDateTime,endDateTime);
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCars(getCarsPayload));
    }
    @GetMapping("/getCarsByFilter/{filterType}")
    public ResponseEntity<List<Car>> getCarsByFilter(@PathVariable String filterType)
    {
        switch (filterType){

            case "hatchback", "luxury", "sedan", "suv":
                List<Car>carsByCarType=  carService.getFilteredCarsByCarType(filterType);
                return ResponseEntity.status(HttpStatus.OK).body(carsByCarType);
            case "4 Seats":
                List<Car>carsBy4Seats=carService.getFilteredCarsBySeats(4);
                return ResponseEntity.status(HttpStatus.OK).body(carsBy4Seats);
            case "5 Seats":
                List<Car>carsBy5Seats=carService.getFilteredCarsBySeats(5);
                return ResponseEntity.status(HttpStatus.OK).body(carsBy5Seats);
            case "7 Seats":
                List<Car>carsBy7Seats=carService.getFilteredCarsBySeats(7);
                return ResponseEntity.status(HttpStatus.OK).body(carsBy7Seats);
            case "Petrol","Diesel","CNG","Electric":
                if(filterType.equals("Electric"))
                {
                    List<Car>carsByFuelType=carService.getFilteredCarsByFuelType("EV");
                    return ResponseEntity.status(HttpStatus.OK).body(carsByFuelType);
                }
                List<Car>carsByFuelType=carService.getFilteredCarsByFuelType(filterType);
                return ResponseEntity.status(HttpStatus.OK).body(carsByFuelType);
            case "Manual","Automatic":
                if(filterType.equals("Automatic"))
                {
                    List<Car>carsByTransmissionType=carService.getFilteredCarsByTransmissionType("Auto");
                    return ResponseEntity.status(HttpStatus.OK).body(carsByTransmissionType);
                }
                List<Car>carsByTransmissionType=carService.getFilteredCarsByTransmissionType(filterType);
                return ResponseEntity.status(HttpStatus.OK).body(carsByTransmissionType);
            case "3","4","All":
                List<Car>carsByRatings=carService.getFilteredCarsByRatings(filterType);
                return ResponseEntity.status(HttpStatus.OK).body(carsByRatings);
        }
        return null;
    }

    @GetMapping("/getCarsByName/{carName}")
    public ResponseEntity<Car> getCarsByName(@PathVariable String carName) {
        System.out.println(carName);
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarByCarName(carName));
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody AddCarPayLoad addCarPayLoad){

        Car car=carService.addCar(addCarPayLoad);
        if(car!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(car);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    };

}

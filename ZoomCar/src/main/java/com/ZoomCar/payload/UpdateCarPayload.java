package com.ZoomCar.payload;

import com.ZoomCar.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateCarPayload {

    private String name;
    private Integer carId;
    private String image;
    private String transmissionType;
    private String fuelType;
    private String ratings;
    private String address;
    private Double distanceTravelled;
    private Integer noOfSeats;
    private String carType;
    private double discountPrice;
    private double originalPrice;
    private Integer userId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private CarStatus status;

}
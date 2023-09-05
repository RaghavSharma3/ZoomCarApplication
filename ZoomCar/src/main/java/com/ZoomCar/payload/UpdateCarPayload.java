package com.ZoomCar.payload;

import com.ZoomCar.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateCarPayload {

    private Integer carId;
    private String name;
    private String model;
    private Integer year;
    private String transmissionType;
    private String fuelType;
    private Double distanceTravelled;
    private CarStatus status;
    private Integer noOfSeats;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}

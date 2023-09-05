package com.ZoomCar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCarPayLoad {

    private String name;
    private String model;
    private Integer year;
    private String transmissionType;
    private String fuelType;
    private Double distanceTravelled;
    private Integer noOfSeats;

    private String startDateTime;

    private Integer pricePerHour;
    private String endDateTime;
    private Integer userId;


}

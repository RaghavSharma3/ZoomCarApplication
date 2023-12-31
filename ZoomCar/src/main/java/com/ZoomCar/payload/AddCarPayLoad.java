package com.ZoomCar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarPayLoad {

    private String name;
    private String image;
    private String transmission;
    private String fuel;
    private String ratings;
    private String address;
    private Double kms;
    private Integer seats;
    private String carType;
    private double discount_price;
    private double original_price;
    private String userId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


}

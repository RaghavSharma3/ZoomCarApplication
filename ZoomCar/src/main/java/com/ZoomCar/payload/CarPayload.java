package com.ZoomCar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPayload {
    private String name;
    private String image;
    private String transmission;
    private String fuel;
    private String ratings;
    private String address;
    private Double kms;
    private Integer seats;
    private double discount_price;
    private double original_price;
    private String userId;
}

package com.ZoomCar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCarPayload {
    private Integer carId;
    private Integer userId;
    private String startTime;
    private String endTime;
}

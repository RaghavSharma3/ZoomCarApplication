package com.ZoomCar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCarsPayload {
    private String startDateTime;
    private String endDateTime;
}

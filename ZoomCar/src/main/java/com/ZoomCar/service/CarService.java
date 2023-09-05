package com.ZoomCar.service;

import com.ZoomCar.entity.Car;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;
import com.ZoomCar.payload.UpdateCarPayload;

import java.util.List;

public interface CarService {
	List<Car> getCars(GetCarsPayload getCarsPayload);
	public String addCar(AddCarPayLoad addCarPayLoad);
	public String updateCar(UpdateCarPayload updatedCar);
}

package com.ZoomCar.service;

import com.ZoomCar.entity.Car;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;

import java.util.List;

public interface CarService {
	List<Car> getCars(GetCarsPayload getCarsPayload);
	public Car addCar(AddCarPayLoad addCarPayLoad);
	public List<Car> getFilteredCarsByCarType(String carType);
	public List<Car> getFilteredCarsBySeats(Integer seats);
	public List<Car> getFilteredCarsByFuelType(String fuel);
	public List<Car> getFilteredCarsByTransmissionType(String fuel);
	public List<Car> getFilteredCarsByRatings(String rating);
	public Car getCarByCarName(String carName);
}

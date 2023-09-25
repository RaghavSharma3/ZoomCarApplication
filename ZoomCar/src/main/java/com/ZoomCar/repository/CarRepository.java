package com.ZoomCar.repository;

import com.ZoomCar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    public List<Car> findAllByCarType(String carType);

    public List<Car> findAllBySeats(Integer seats);
    public List<Car> findAllByFuel(String fuel);
    public List<Car> findAllByTransmission(String transmission);
    public Car findByNameAndAddressAndRatingsAndTransmissionAndFuel(String name, String address, String ratings, String transmission, String fuel);
    public Car findByName(String name);

}
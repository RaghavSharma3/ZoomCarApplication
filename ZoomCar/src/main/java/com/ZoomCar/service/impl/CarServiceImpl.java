package com.ZoomCar.service.impl;

import com.ZoomCar.entity.Car;
import com.ZoomCar.entity.CarStatus;
import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;
import com.ZoomCar.payload.UpdateCarPayload;
import com.ZoomCar.repository.CarRepository;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    @Override
    public List<Car> getCars(GetCarsPayload getCarsPayload) {
        return getCarFromDateTime(getCarsPayload);
    }

    public List<Car> getCarFromDateTime(GetCarsPayload getCarsPayload)
    {
        List<Car> cars= carRepository.findAll();
        List<Car> displayCars= new ArrayList<>();
        String startDateTimeStr = getCarsPayload.getStartDateTime();
        String endDateTimeStr = getCarsPayload.getEndDateTime();

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = LocalDateTime.parse(startDateTimeStr);
            endDateTime = LocalDateTime.parse(endDateTimeStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date-time format: " + e.getMessage());
            return null;
        }
        for (Car car : cars) {
            LocalDateTime carStartDateTime = car.getStartDateTime();
            LocalDateTime carEndDateTime = car.getEndDateTime();

            boolean isStartBeforeOrEqual = carStartDateTime.isBefore(startDateTime) || carStartDateTime.isEqual(startDateTime);
            boolean isEndAfterOrEqual = carEndDateTime.isAfter(endDateTime) || carEndDateTime.isEqual(endDateTime);

            System.out.println(isStartBeforeOrEqual);
            System.out.println(isEndAfterOrEqual);
            if (isStartBeforeOrEqual==true && isEndAfterOrEqual==true) {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public String addCar(AddCarPayLoad addCarPayLoad) {

        Optional<User> user= userRepository.findById(addCarPayLoad.getUserId());
            if (user.isPresent() && user.get().getRole()== Role.ROLE_HOST) {
                if(user.get().getIsBlocked().equals("No")) {
                Car car = Car.builder()
                        .name(addCarPayLoad.getName())
                        .model(addCarPayLoad.getModel())
                        .fuelType(addCarPayLoad.getFuelType())
                        .noOfSeats(addCarPayLoad.getNoOfSeats())
                        .year(addCarPayLoad.getYear())
                        .startDateTime(LocalDateTime.parse(addCarPayLoad.getStartDateTime()))
                        .endDateTime(LocalDateTime.parse(addCarPayLoad.getEndDateTime()))
                        .transmissionType(addCarPayLoad.getTransmissionType())
                        .distanceTravelled(addCarPayLoad.getDistanceTravelled())
                        .user(user.get())
                        .pricePerHour(addCarPayLoad.getPricePerHour())
                        .status(CarStatus.Available).build();

                carRepository.save(car);
                return "Car Added Successfully";
            }
                else {
                    return "Host is Blocked, Contact Admin";
                }
        }
        else {
            return "Host from provided HostId Not Found";
        }
    }
    @Override
    public String updateCar(UpdateCarPayload updatedCar) {
        Optional<Car> optionalCar = carRepository.findById(updatedCar.getCarId());

        if (optionalCar.isPresent())
        {
            Car existingCar = optionalCar.get();
            existingCar.setName(updatedCar.getName());
            existingCar.setModel(updatedCar.getModel());
            existingCar.setYear(updatedCar.getYear());
            existingCar.setDistanceTravelled(updatedCar.getDistanceTravelled());
            existingCar.setNoOfSeats(updatedCar.getNoOfSeats());
            existingCar.setFuelType(updatedCar.getFuelType());
            existingCar.setTransmissionType(updatedCar.getTransmissionType());
            existingCar.setStatus(updatedCar.getStatus());
            existingCar.setEndDateTime(updatedCar.getEndDateTime());
            existingCar.setStartDateTime(updatedCar.getStartDateTime());
            carRepository.save(existingCar);
            return "Car Details Updated";
        }
        else {
            return "Car With Provided CarId is not Present";
        }
    }

}

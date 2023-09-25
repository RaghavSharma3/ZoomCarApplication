package com.ZoomCar.service.impl;

import com.ZoomCar.entity.Car;
import com.ZoomCar.entity.CarStatus;
import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;
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
            if (isStartBeforeOrEqual==true && isEndAfterOrEqual==true && car.getStatus()==CarStatus.Available) {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public Car addCar(AddCarPayLoad addCarPayLoad) {

        Optional<User> user= userRepository.findByEmail(addCarPayLoad.getUserId());
        if (user.isPresent()) {
                Car car = Car.builder()
                        .image(addCarPayLoad.getImage())
                        .name(addCarPayLoad.getName())
                        .transmission(addCarPayLoad.getTransmission())
                        .fuel(addCarPayLoad.getFuel())
                        .ratings(addCarPayLoad.getRatings())
                        .address(addCarPayLoad.getAddress())
                        .kms(addCarPayLoad.getKms())
                        .discount_price(addCarPayLoad.getDiscount_price())
                        .original_price(addCarPayLoad.getOriginal_price())
                        .seats(addCarPayLoad.getSeats())
                        .carType(addCarPayLoad.getCarType())
                        .startDateTime(LocalDateTime.parse("2017-01-13T17:09:42.411"))
                        .endDateTime(LocalDateTime.parse("2017-01-14T17:12:42.411"))
                        .user(user.get())
                        .status(CarStatus.Available).build();
                user.get().setRole(Role.ROLE_HOST);
                userRepository.save(user.get());
               return carRepository.save(car);
            }
        else {
            return null;
        }
    }

    @Override
    public List<Car> getFilteredCarsByCarType(String carType)
    {
        List<Car>cars= carRepository.findAllByCarType(carType);
        List<Car>displayCars=new ArrayList<>();
        for(Car car:cars)
        {
            if( car.getStatus()==CarStatus.Available)
            {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public List<Car> getFilteredCarsBySeats(Integer seats)
    {
        List<Car>cars= carRepository.findAllBySeats(seats);
        List<Car>displayCars=new ArrayList<>();
        for(Car car:cars)
        {
            if( car.getStatus()==CarStatus.Available)
            {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public List<Car> getFilteredCarsByFuelType(String fuel)
    {
        List<Car>cars= carRepository.findAllByFuel(fuel);
        List<Car>displayCars=new ArrayList<>();
        for(Car car:cars)
        {
            if( car.getStatus()==CarStatus.Available)
            {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public List<Car> getFilteredCarsByTransmissionType(String transmission)
    {
        List<Car>cars= carRepository.findAllByTransmission(transmission);
        List<Car>displayCars=new ArrayList<>();
        for(Car car:cars)
        {
            if( car.getStatus()==CarStatus.Available)
            {
                displayCars.add(car);
            }
        }
        return displayCars;
    }
    @Override
    public List<Car> getFilteredCarsByRatings(String rating)
    {
        List<Car>cars= carRepository.findAll();
        List<Car>ratedCars= new ArrayList<>();
        String s="";
        for(int i=0;i<cars.size();i++)
        {
            for(int k=0;k< cars.get(i).getRatings().length();k++)
            {
                if(cars.get(i).getRatings().charAt(k)!='(')
                {
                    s=s+cars.get(i).getRatings().charAt(k);
                }
                else if(cars.get(i).getRatings().charAt(k)=='(')
                {
                    break;
                }
            }
            double ratings= Double.parseDouble(s);
            s="";
            if(rating.equals("3"))
            {
                if(ratings>3 && cars.get(i).getStatus()==CarStatus.Available){
                    ratedCars.add(cars.get(i));
                }
            }
            else if (rating.equals("4"))
            {
                if(ratings>4 && cars.get(i).getStatus()==CarStatus.Available){
                    ratedCars.add(cars.get(i));
                }
            }
            else {
                if(cars.get(i).getStatus()==CarStatus.Available) {
                    ratedCars.add(cars.get(i));
                }
            }
        }
        return ratedCars;
    }
    @Override
    public Car getCarByCarName(String carName)
    {

        Car car= carRepository.findByName(carName);
        if(car.getStatus()==CarStatus.Available)
        {
            return car;
        }
        return null;
    }
}

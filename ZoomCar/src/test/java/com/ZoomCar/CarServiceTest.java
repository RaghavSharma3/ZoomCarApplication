package com.ZoomCar;

import com.ZoomCar.entity.Car;
import com.ZoomCar.entity.CarStatus;
import com.ZoomCar.entity.User;
import com.ZoomCar.payload.AddCarPayLoad;
import com.ZoomCar.payload.GetCarsPayload;
import com.ZoomCar.repository.CarRepository;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.impl.CarServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private CarRepository carRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void getCarByName()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        Mockito.when(carRepository.findByName("myCar")).thenReturn(car);

        Assertions.assertNotNull(carService.getCarByCarName("myCar"));
    }
    @Test
    public void getCarByNameNull()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        Mockito.when(carRepository.findByName("myCar")).thenReturn(car);

        Assertions.assertNull(carService.getCarByCarName("myCar"));
    }
    @Test
    public void getCarFromDateTime()
    {
        GetCarsPayload getCarsPayload=new GetCarsPayload("12/3/15","13/4/15");
        Car car=new Car();
        List<Car> carList=new ArrayList<>();
        Assertions.assertNull(carService.getCarFromDateTime(getCarsPayload));
    }
    @Test
    public void addCar()
    {
        AddCarPayLoad addCarPayLoad=new AddCarPayLoad();
        User user=new User();
        user.setUserID(1234);
        Mockito.when(userRepository.findByEmail(addCarPayLoad.getUserId())).thenReturn(Optional.of(user));
        Assertions.assertNull(carService.addCar(addCarPayLoad));
    }
    @Test
    public void addCarNull()
    {
        AddCarPayLoad addCarPayLoad=new AddCarPayLoad();
        User user=new User();
        user.setUserID(1234);
        Mockito.when(userRepository.findByEmail(addCarPayLoad.getUserId())).thenReturn(Optional.empty());
        Assertions.assertNull(carService.addCar(addCarPayLoad));
    }
    @Test
    public void getFilteredCarsByType()
    {
        Car car=new Car();
        List<Car> carList=new ArrayList<>();
        car.setStatus(CarStatus.Available);
        carList.add(car);
        Mockito.when(carRepository.findAllByCarType(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByCarType("cartype"));

    }
    @Test
    public void getFilteredCarsByTypeUnavailable()
    {
        Car car=new Car();
        List<Car> carList=new ArrayList<>();
        car.setStatus(CarStatus.Unavailable);
        carList.add(car);
        Mockito.when(carRepository.findAllByCarType(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByCarType("cartype"));

    }
    @Test
    public void getFilteredCarsBySeats()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllBySeats(5)).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsBySeats(5));
    }
    @Test
    public void getFilteredCarsBySeatsUnavailable()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllBySeats(5)).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsBySeats(5));
    }
    @Test
    public void getFilteredCarsByFuelType()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllByFuel(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByFuelType("fuel"));
    }
    @Test
    public void getFilteredCarsByFuelTypeUnavailable()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllByFuel(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByFuelType("fuel"));
    }
    @Test
    public void getFilteredCarsByTransmissionType()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllByTransmission(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByTransmissionType("dummyString"));
    }
    @Test
    public void getFilteredCarsByTransmissionTypeUnavailable()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAllByTransmission(Mockito.anyString())).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByTransmissionType("dummyString"));
    }
    @Test
    public void getFilteredCarsByRatings3()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        car.setRatings("3");
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByRatings("2"));
    }
    @Test
    public void getFilteredCarsByRatings3Unavailable()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        car.setRatings("3");
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByRatings("2"));
    }
    @Test
    public void getFilteredCarsByRatings4()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Available);
        car.setRatings("3");
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByRatings("2"));
    }
    @Test
    public void getFilteredCarsByRatings4Unavailable()
    {
        Car car=new Car();
        car.setStatus(CarStatus.Unavailable);
        car.setRatings("3");
        List<Car> carList=new ArrayList<>();
        carList.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        Assertions.assertNotNull(carService.getFilteredCarsByRatings("2"));
    }



}

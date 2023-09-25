package com.ZoomCar;

import com.ZoomCar.entity.Booking;
import com.ZoomCar.entity.BookingStatus;
import com.ZoomCar.entity.Car;
import com.ZoomCar.entity.Payment;
import com.ZoomCar.payload.BookingCarPayload;
import com.ZoomCar.payload.CarPayload;
import com.ZoomCar.payload.ConfirmBookingPayload;
import com.ZoomCar.repository.BookingRepository;
import com.ZoomCar.repository.CarRepository;
import com.ZoomCar.repository.PaymentRepository;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.impl.BookingServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void BookingCarNull()
    {
        BookingCarPayload bookingCarPayload=new BookingCarPayload();
        CarPayload carPayload=new CarPayload();
        carPayload.setName("carName");
        bookingCarPayload.setCarId(carPayload);
        Car car=new Car();
        car.setId(123);
        Assertions.assertNull(bookingService.bookingCar(bookingCarPayload));
    }
    @Test
    public void BookingCancellation()
    {
        Booking booking=new Booking();
        Car car=new Car();
        booking.setCarId(car);
        Mockito.when(bookingRepository.findById(123)).thenReturn(Optional.of(booking));
        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
        Assertions.assertNotNull(bookingService.bookingCancellation(123));
    }
    @Test
    public void BookingCancellationNull()
    {
        Booking booking=new Booking();
        Car car=new Car();
        booking.setCarId(car);
        Mockito.when(bookingRepository.findById(123)).thenReturn(Optional.empty());
//        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
        Assertions.assertNull(bookingService.bookingCancellation(123));
    }
    @Test
    public void getBookings()
    {
        Booking booking=new Booking();
        booking.setStatus(BookingStatus.Confirmed);
        booking.setUserId("123");
        Car car=new Car();
        ArrayList<Booking> bookingList=new ArrayList<>();
        bookingList.add(booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assertions.assertNotNull(bookingService.getBookings("123"));
    }
    @Test
    public void getBookingsCancel()
    {
        Booking booking=new Booking();
        booking.setStatus(BookingStatus.Cancelled);
        booking.setUserId("123");
        Car car=new Car();
        ArrayList<Booking> bookingList=new ArrayList<>();
        bookingList.add(booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assertions.assertNotNull(bookingService.getBookings("123"));
    }
    @Test
    public void getBookingsUserNotFound()
    {
        Booking booking=new Booking();
        booking.setStatus(BookingStatus.Confirmed);
        booking.setUserId("12");
        Car car=new Car();
        ArrayList<Booking> bookingList=new ArrayList<>();
        bookingList.add(booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assertions.assertNotNull(bookingService.getBookings("123"));
    }
    @Test
    public void getBookingsUserNotFoundNotConfirm()
    {
        Booking booking=new Booking();
        booking.setStatus(BookingStatus.Cancelled);
        booking.setUserId("12");
        Car car=new Car();
        ArrayList<Booking> bookingList=new ArrayList<>();
        bookingList.add(booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assertions.assertNotNull(bookingService.getBookings("123"));
    }
    @Test
    public void confirmBooking()
    {
        Booking booking=new Booking();
        ConfirmBookingPayload confirmBookingPayload=new ConfirmBookingPayload();
        confirmBookingPayload.setBookingId(123);
        Payment payment=new Payment();
        Car car=new Car();
        car.setId(123);
        booking.setCarId(car);
        Mockito.when(bookingRepository.findById(123)).thenReturn(Optional.of(booking));
        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);
        Assertions.assertNotNull(bookingService.confirmBooking(confirmBookingPayload));


    }
    @Test
    public void confirmBookingNull()
    {
        Booking booking=new Booking();
        ConfirmBookingPayload confirmBookingPayload=new ConfirmBookingPayload();
        confirmBookingPayload.setBookingId(123);
        Payment payment=new Payment();
        Car car=new Car();
        car.setId(123);
        booking.setCarId(car);
        Mockito.when(bookingRepository.findById(123)).thenReturn(Optional.empty());
//        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);
        Assertions.assertNull(bookingService.confirmBooking(confirmBookingPayload));


    }

}

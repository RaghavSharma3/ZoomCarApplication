package com.ZoomCar.service;

import com.ZoomCar.entity.Booking;

import java.util.List;

public interface BookingService {
    
    String cancelBooking(Integer bookingId);
	List<Booking> listBookings();
	String bookCar(Integer carId, Integer userId, String startTime, String endTime);
}


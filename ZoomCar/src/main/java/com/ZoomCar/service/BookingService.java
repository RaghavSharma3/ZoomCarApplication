package com.ZoomCar.service;

import com.ZoomCar.entity.Booking;
import com.ZoomCar.entity.Payment;
import com.ZoomCar.payload.BookingCarPayload;
import com.ZoomCar.payload.ConfirmBookingPayload;

import java.util.List;

public interface BookingService {
	public Booking bookingCar(BookingCarPayload bookingCarPayload);
	public Booking bookingCancellation(Integer bookingId);
	public List<Booking> getBookings(String userId);
	public Payment confirmBooking(ConfirmBookingPayload confirmBookingPayload);
}


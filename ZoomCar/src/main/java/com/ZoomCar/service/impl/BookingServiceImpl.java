package com.ZoomCar.service.impl;

import com.ZoomCar.entity.*;
import com.ZoomCar.payload.BookingCarPayload;
import com.ZoomCar.payload.ConfirmBookingPayload;
import com.ZoomCar.repository.*;
import com.ZoomCar.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final CarRepository carRepository;
	private final UserRepository userRepository;
	private final BookingRepository bookingRepository;
	private final PaymentRepository paymentRepository;

	@Override
	public Booking bookingCar(BookingCarPayload bookingCarPayload)
	{
		String name= bookingCarPayload.getCarId().getName();
		String address= bookingCarPayload.getCarId().getAddress();
		String ratings= bookingCarPayload.getCarId().getRatings();
		String transmission= bookingCarPayload.getCarId().getTransmission();
		String fuel= bookingCarPayload.getCarId().getFuel();
		String userId= bookingCarPayload.getCarId().getUserId();
		Car car= carRepository.findByNameAndAddressAndRatingsAndTransmissionAndFuel(name,address,ratings,transmission,fuel);
		if(car!=null)
		{
			Booking booking= Booking.builder()
					.carId(car)
					.status(BookingStatus.Pending)
					.userId(userId)
					.build();
			bookingRepository.save(booking);
			return booking;
		}
		return null;
	}
	@Override
	public Booking bookingCancellation(Integer bookingId)
	{
		Optional<Booking> booking= bookingRepository.findById(bookingId);
		if(booking.isPresent())
		{
			booking.get().setStatus(BookingStatus.Cancelled);
			Car car=booking.get().getCarId();
			car.setStatus(CarStatus.Available);
			carRepository.save(car);
			return bookingRepository.save(booking.get());
		}
		return null;
	}
	@Override
	public List<Booking> getBookings(String userId)
	{
		List<Booking>bookings=new ArrayList<>();
		List<Booking>allBookings= bookingRepository.findAll();
		for(int i=0;i<allBookings.size();i++)
		{
			if(allBookings.get(i).getStatus()==BookingStatus.Confirmed&& allBookings.get(i).getUserId().equals(userId)) {
				bookings.add(allBookings.get(i));
			}
		}
		return bookings;
	}
	@Override
	public Payment confirmBooking(ConfirmBookingPayload confirmBookingPayload)
	{
		Optional<Booking>booking= bookingRepository.findById(confirmBookingPayload.getBookingId());
		if(booking.isPresent())
		{
			booking.get().setStatus(BookingStatus.Confirmed);
			bookingRepository.save(booking.get());
			Payment payment= Payment.builder()
					.amount(confirmBookingPayload.getAmount())
					.bookingId(booking.get())
					.build();
			booking.get().getCarId().setStatus(CarStatus.Unavailable);
			carRepository.save(booking.get().getCarId());
			return paymentRepository.save(payment);
		}
		return null;
	}
}

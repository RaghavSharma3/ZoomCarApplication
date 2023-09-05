package com.ZoomCar.service.impl;

import com.ZoomCar.entity.*;
import com.ZoomCar.repository.BookingRepository;
import com.ZoomCar.repository.CarRepository;
import com.ZoomCar.repository.UserRepository;
import com.ZoomCar.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;
	private final UserRepository userRepository;


	@Override
	public String bookCar(Integer carId, Integer userId, String startTime, String endTime) {
		// Check if the car and user exist in the database
		Optional<Car> optionalCar = carRepository.findById(carId);
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalCar.isPresent() && optionalUser.isPresent()&&optionalUser.get().getRole()==Role.ROLE_USER) {
			Car car = optionalCar.get();
			User user = optionalUser.get();

			if (user.getIsBlocked().equals("No")) {
				// Check if the car is available for booking
				if (car.getStatus() == CarStatus.Available) {
					// Create a new Booking object
					Booking booking = new Booking();
					booking.setCar(car);
					booking.setUser(user);
					booking.setBookingTime(LocalDateTime.now());
					booking.setPrice(calculatePriceForBooking(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), car));
					booking.setStatus(BookingStatus.Confirmed);

					booking = bookingRepository.save(booking);

					car.setStatus(CarStatus.Unavailable);
					carRepository.save(car);

					return "Booking Done Successfully";
				} else {
					// Car is not available for booking
					return("The selected car is not available for booking.");
				}
			}
			else {
				return "User is Blocked, contact Admin!";
			}
		} else {
			// Car or user not found in the database
			return("Car or user not found.");
		}
	}
	private Integer calculatePriceForBooking(LocalDateTime startTime, LocalDateTime endTime, Car car) {

		double pricePerMinute = car.getPricePerHour()/60;

		// Calculate the duration in minutes
		long minutes = Duration.between(startTime, endTime).toMinutes();

		// Calculate the total price
		double totalPrice = minutes * pricePerMinute;

		return (int) totalPrice;
	}

	@Override
	public String cancelBooking(Integer bookingId) {
		// Use the bookingRepository to fetch the booking by its ID
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

		if (optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();

			// Check if the booking is in a cancellable state
			if (booking.getStatus() != BookingStatus.Cancelled) {

				booking.setStatus(BookingStatus.Cancelled);

				Car car = booking.getCar();
				car.setStatus(CarStatus.Available);

				// Optionally, handle refund logic if necessary
				// For example, you can refund the payment to the user if applicable

				carRepository.save(car);
				bookingRepository.save(booking);

				return "Booking Cancelled Successfully";
			} else {

				return ("Booking is already canceled with ID: " + bookingId);
			}
		} else {

			return ("Booking not found with ID: " + bookingId);
		}
	}

	@Override
	public List<Booking> listBookings() {
		// fetch a list of bookings from the database
		List<Booking>bookings=new ArrayList<>();
		for(int i=0;i<bookingRepository.findAll().size();i++)
		{
			if(bookingRepository.findAll().get(i).getStatus()==BookingStatus.Confirmed) {
				bookings.add(bookingRepository.findAll().get(i));
			}
		}
		return bookings;
	}

}

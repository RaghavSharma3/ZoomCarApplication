package com.ZoomCar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer carId;
	
	private String name;
	private String model;
	private Year year;
	
	@Column(name = "transmission_type")
	private TransmissionType transmissionType;
	
	@Column(name = "fuel_type")
	private FuelType fuelType;
	
	@Column(name = "distance_travelled")
	private Double distanceTravelled;
	private CarStatus status;
	
	@Column(name = "no_of_seats")
	private Integer noOfSeats;
	
	@Column(name = "start_time")
	private LocalDateTime startDateTime;
	
	@Column(name = "end_time")
	private LocalDateTime endDateTime;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Booking> bookings;

}

package com.ZoomCar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer carId;
	
	private String name;
	private String model;
	private Integer year;
	
	@Column(name = "transmission_type")
	private String transmissionType;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Column(name = "distance_travelled")
	private Double distanceTravelled;
	@Enumerated(EnumType.STRING)
	private CarStatus status;

	@Column(name="price_perHour")
	private Integer pricePerHour;
	@Column(name = "no_of_seats")
	private Integer noOfSeats;
	
	@Column(name = "start_time")
	private LocalDateTime startDateTime;
	
	@Column(name = "end_time")
	private LocalDateTime endDateTime;
	@JsonIgnore
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<Booking> bookings;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

}

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
	@Column(name = "_id")
	private Integer id;

	private String image;
	private String name;

	@Column(name = "transmission")
	private String transmission;

	@Column(name = "fuel")
	private String fuel;

	private String ratings;
	private String address;

	@Column(name = "kms")
	private Double kms;

	@Enumerated(EnumType.STRING)
	private CarStatus status;

	@Column(name = "discount_price")
	private double discount_price;

	@Column(name = "original_price")
	private double original_price;

	@Column(name = "seats")
	private Integer seats;

	@Column(name = "car_type")
	private String carType;

	@Column(name = "start_time")
	private LocalDateTime startDateTime;

	@Column(name = "end_time")
	private LocalDateTime endDateTime;

	@JsonIgnore
	@OneToMany(mappedBy = "carId", cascade = CascadeType.ALL)
	private List<Booking> booking;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}
package com.ZoomCar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;
	private Double amount;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "booking_id")
	@JsonIgnore
	private Booking bookingId;

}

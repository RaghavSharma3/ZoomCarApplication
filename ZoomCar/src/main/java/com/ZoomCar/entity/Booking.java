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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Car carId;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String userId;
    @OneToOne(cascade = CascadeType.MERGE,mappedBy = "bookingId")
    @JsonIgnore
    private Payment payment;


}

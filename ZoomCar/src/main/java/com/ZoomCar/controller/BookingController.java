
package com.ZoomCar.controller;

import com.ZoomCar.entity.Booking;
import com.ZoomCar.entity.Payment;
import com.ZoomCar.payload.BookingCarPayload;
import com.ZoomCar.payload.ConfirmBookingPayload;
import com.ZoomCar.service.BookingService;
import com.ZoomCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final CarService carService;

    @PostMapping("/bookCar")
    public ResponseEntity<Booking> bookACar(@RequestBody BookingCarPayload bookingCarPayload){
        Booking booking= bookingService.bookingCar(bookingCarPayload);
        if(booking!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/cancelCarBooking/{bookingId}")
    public ResponseEntity<Booking> cancelCarBooking(@PathVariable Integer bookingId){
        Booking booking= bookingService.bookingCancellation(bookingId);
        if(booking!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @GetMapping("/listBookings/{userId}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookings(userId));
    }
    @PostMapping("/confirmBooking")
    public ResponseEntity<Payment> confirmBooking(@RequestBody ConfirmBookingPayload confirmBookingPayload)
    {
        Payment payment= bookingService.confirmBooking(confirmBookingPayload);
        if(payment!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}


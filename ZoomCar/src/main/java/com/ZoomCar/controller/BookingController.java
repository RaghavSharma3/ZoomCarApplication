
package com.ZoomCar.controller;

import com.ZoomCar.entity.Booking;
import com.ZoomCar.payload.BookCarPayload;
import com.ZoomCar.service.BookingService;
import com.ZoomCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final CarService carService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/book")
    public ResponseEntity<String> bookCar(
            @RequestBody BookCarPayload bookCarPayload
            ) {
        String response = bookingService.bookCar(bookCarPayload.getCarId(), bookCarPayload.getUserId(), bookCarPayload.getStartTime(), bookCarPayload.getEndTime());
        if(response.equals("Booking Done Successfully"))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        else if(response.equals("The selected car is not available for booking."))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(response.equals("User is Blocked, contact Admin!"))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
        String response= bookingService.cancelBooking(bookingId);
        if(response.equals("Booking Cancelled Successfully"))
        {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/list")
    public ResponseEntity<List<Booking>> listBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.listBookings());
    }
}


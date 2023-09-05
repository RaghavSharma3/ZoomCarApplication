package com.ZoomCar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingAlreadyCanceledException extends RuntimeException {

    public BookingAlreadyCanceledException(String message) {
        super(message);
    }
}

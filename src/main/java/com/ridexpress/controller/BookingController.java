package com.ridexpress.controller;

import com.ridexpress.entity.Booking;
import com.ridexpress.entity.Car;
import com.ridexpress.service.BookingService;
import com.ridexpress.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class BookingController {

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String showBookingPage(@RequestParam(value = "carId", required = false) String carId, Model model) {
        if (carId != null) {
            Car car = carService.getCarById(carId);
            if (car == null) {
                model.addAttribute("error", "Car not found!");
                return "error-page";
            }
            model.addAttribute("car", car);

            Booking booking = new Booking();
            booking.setCarId(car.getCarId());  
            model.addAttribute("booking", booking);
        }
        return "booking";
    }

    

    @PostMapping("/booking/confirm")
    public String confirmBooking(@ModelAttribute Booking booking, Model model) {
        LocalDate start = booking.getStartDate();
        LocalDate end = booking.getEndDate();
        if (start == null || end == null || end.isBefore(start)) {
            model.addAttribute("error", "Invalid start or end date.");
            return "booking";
        }

        Car car = carService.getCarById(booking.getCarId());
        if (car == null) {
            model.addAttribute("error", "Selected car not found.");
            return "booking";
        }

        long days = ChronoUnit.DAYS.between(start, end);
        if (days == 0) days = 1;
        booking.setTotalPrice(days * car.getRentalPricePerDay());

        try {
            bookingService.saveBooking(booking);
            model.addAttribute("message", "Booking Confirmed!");
            model.addAttribute("booking", booking);
            return "booking-success";
        } catch (Exception e) {
            model.addAttribute("error", "Booking failed: " + e.getMessage());
            return "booking";
        }
    }
}

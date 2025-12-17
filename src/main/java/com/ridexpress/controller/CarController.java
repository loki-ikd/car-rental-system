package com.ridexpress.controller;

import com.ridexpress.entity.Car;
import com.ridexpress.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    
    @GetMapping("/cars")
    public String listCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String search,
            Model model) {

        List<Car> cars = carService.searchCars(brand, minPrice, maxPrice, search);

        model.addAttribute("cars", cars);
        model.addAttribute("brand", brand);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("search", search);

        return "cars";
    }
    
    
    @GetMapping("/cars/details")
    public String carDetails(@RequestParam("id") String carId, Model model) {
        Car car = carService.getCarById(carId);

        if (car == null) {
            return "error"; 
        }

        model.addAttribute("car", car);
        return "car-details";
    }
    @GetMapping("/car/details/{carId}")
    public String showCarDetails(@PathVariable String carId, Model model) {
        Car car = carService.getCarById(carId);

        if (car == null) {
            return "redirect:/cars"; 
        }

        model.addAttribute("car", car);
        return "car-details";
    }

}

package com.ridexpress.repository;

import com.ridexpress.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

//    Car findByCarId(String carId);
//
//    List<Car> findByAvailable(boolean available);
//
//    List<Car> findByLocation_City(String city);
//
//    List<Car> findByRentalPricePerDayBetween(double minPrice, double maxPrice);
//}
}

package com.ridexpress.service;

import com.ridexpress.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private MongoTemplate mongoTemplate;

    // ✅ Search cars with filters (no pagination)
    public List<Car> searchCars(String brand, Double minPrice, Double maxPrice, String search) {
        Query query = new Query();

        if (brand != null && !brand.isEmpty()) {
            query.addCriteria(Criteria.where("brand").regex(brand, "i"));
        }

        if (minPrice != null && maxPrice != null) {
            query.addCriteria(Criteria.where("rentalPricePerDay").gte(minPrice).lte(maxPrice));
        } else if (minPrice != null) {
            query.addCriteria(Criteria.where("rentalPricePerDay").gte(minPrice));
        } else if (maxPrice != null) {
            query.addCriteria(Criteria.where("rentalPricePerDay").lte(maxPrice));
        }

        if (search != null && !search.isEmpty()) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("brand").regex(search, "i"),
                    Criteria.where("model").regex(search, "i")
            ));
        }

        return mongoTemplate.find(query, Car.class);
    }
    
    public Car getCarById(String carId) {
        Query query = new Query(Criteria.where("carId").is(carId));
        return mongoTemplate.findOne(query, Car.class);
    }

}

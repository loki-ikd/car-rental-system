package com.ridexpress.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cars")
public class Car {

    @Id
    private String id;

    @Indexed(unique = true)
    private String carId;

    private String brand;
    private String model;
    private String type;  
    private double rentalPricePerDay;
    private boolean available;

    private String image;

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

    private Location location;

    private List<String> features;
    private double rating;

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    
    public static class Location {
        private String city;

        @GeoSpatialIndexed
        private double[] coordinates; 

        public Location() {}

        public Location(String city, double[] coordinates) {
            this.city = city;
            this.coordinates = coordinates;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double[] getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(double[] coordinates) {
            this.coordinates = coordinates;
        }
    }
}

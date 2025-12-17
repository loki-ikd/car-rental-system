package com.ridexpress;

import com.ridexpress.entity.*;
import com.ridexpress.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RideXpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideXpressApplication.class, args);
    }

        
}

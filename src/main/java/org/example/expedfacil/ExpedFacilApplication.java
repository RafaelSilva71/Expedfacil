package org.example.expedfacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExpedFacilApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpedFacilApplication.class, args);
    }

}

package com.example.reservationservice.feignClient;

import com.example.reservationservice.models.Salle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "salle-service",url = "http://localhost:8082" , configuration = FeignClientConfig.class)
public interface SalleClient {
    @GetMapping("/api/salles/{id}")
    Salle getSalleById(@PathVariable Long id);
}
package com.example.reservationservice.feignClient;

import com.example.reservationservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://localhost:8081" , configuration = FeignClientConfig.class)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}
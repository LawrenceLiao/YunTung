package com.yuntung.controllers;

import com.yuntung.dtos.SubscriptionDto;
import com.yuntung.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscription/{email}")
    public ResponseEntity getSubscriptions (@PathVariable String email) {

        List<SubscriptionDto> listOfSubscription = subscriptionService.fetchListOfSubscription(email);
        return new ResponseEntity(listOfSubscription, HttpStatus.OK);
    }

    @PostMapping("/subscription")
    public ResponseEntity subscribe(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.subscribe(subscriptionDto);
        return ResponseEntity.ok("Subscribed successfully");
    }

    @DeleteMapping("/subscription")
    public ResponseEntity unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.unsubscribe(subscriptionDto);
        return ResponseEntity.ok("Deleted successfully");
    }
}

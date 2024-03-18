package com.example.SpringBootCustomQuery2.controllers;

import com.example.SpringBootCustomQuery2.Status;
import com.example.SpringBootCustomQuery2.entities.Flight;
import com.example.SpringBootCustomQuery2.repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightRepo flightRepo;

    @PostMapping("/addn")
    public ResponseEntity<List<Flight>> provisioningFiftyFlight(@RequestParam(defaultValue = "100", required = false) Integer n) {
        List<Flight> flightList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            Flight flight = new Flight();
            flight.setDescription("description: " + random());
            flight.setFromAirport("from: " + random());
            flight.setToAirport("to: " + random());
            flight.setStatus(Status.ONTIME);
            flightList.add(flight);
        }
        return ResponseEntity.ok(flightRepo.saveAll(flightList));
    }

    @GetMapping("/sort")
    public List<Flight> getAllSort(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "100") Integer size) {
        return flightRepo.findAllByOrderByFromAirportAsc(PageRequest.of(page, size));
    }

    @GetMapping("/ontime")
    public List<Flight> getOnTime() {
        return flightRepo.findAllByStatus(Status.ONTIME);
    }

    @GetMapping("/statusList")
    public List<Flight> get(@RequestParam Status p1, @RequestParam Status p2) {
        return flightRepo.findAllByStatusList(Arrays.asList(p1, p2));
    }
    private String random() {
        Random random = new Random();
        return random.ints(5, 0, 100)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
package com.example.judesys.controllers;

import com.example.judesys.contracts.CityRequest;
import com.example.judesys.contracts.CityResponse;
import com.example.judesys.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/city")
@Validated
@AllArgsConstructor
public class CityController {

    public final CityService service;

    @CrossOrigin
    @PostMapping(value = "/addCity", produces = "application/json")
    public ResponseEntity<CityResponse> addCity(@RequestBody @Valid CityRequest city) {
        return new ResponseEntity<>(service.saveCity(city), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "{id}")
    public ResponseEntity<CityResponse> getCity(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.geCityById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateCity/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable("id") long id, @RequestBody @Valid CityRequest city) {
        return new ResponseEntity<>(service.updateCity(city, id), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteCity/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable("id") long id) {
        service.deleteCity(id);
        return new ResponseEntity<>("City Deleted Successfully", HttpStatus.OK);
    }
}

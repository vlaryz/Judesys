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
import java.util.List;


@RestController
@RequestMapping("/api/cities")
@Validated
@AllArgsConstructor
public class CityController {

    public final CityService service;

    @CrossOrigin
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CityResponse> addCity(@RequestBody @Valid CityRequest city) {
        return new ResponseEntity<>(service.saveCity(city), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<CityResponse> getCity(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.geCityById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/")
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(service.getAllCities(), HttpStatus.OK);
//        return new ResponseEntity<>(, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable("id") long id, @RequestBody @Valid CityRequest city) {
        System.out.println("AAAAAAAAAA");
        var result = service.updateCity(city, id);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") long id) {
        var result = service.deleteCity(id);
        if(!result)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.judesys.controllers;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;
import com.example.judesys.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@Validated
@AllArgsConstructor
public class EventController {

    public final EventService service;

    @CrossOrigin
    @PostMapping(value = "/{cityId}/addEvent", produces = "application/json")
    public ResponseEntity<EventResponse> addEvent(@PathVariable (value = "cityId") Long cityId, @RequestBody @Valid EventRequest event) {
        return new ResponseEntity<>(service.saveEvent(event, cityId), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.getEventById(id), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(value = "/")
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return new ResponseEntity<>(service.getAllEvents(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateEvent/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable("id") long id, @RequestBody @Valid EventRequest event) {
        return new ResponseEntity<>(service.updateEvent(event, id), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteEvent/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") long id) {
        service.deleteEvent(id);
        return new ResponseEntity<>("Event Deleted Successfully", HttpStatus.OK);
    }
}

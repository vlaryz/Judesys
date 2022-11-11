package com.example.judesys.controllers;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;
import com.example.judesys.services.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities/{cityId}/events")
@Validated
@AllArgsConstructor
@Log4j2
public class EventController {
    public final EventService service;

    @CrossOrigin
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<EventResponse> addEvent(@PathVariable (value = "cityId") Long cityId, @RequestBody @Valid EventRequest event) {
        return new ResponseEntity<>(service.saveEvent(event, cityId), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable (value = "cityId") Long cityId, @PathVariable("id") long id) {
        return new ResponseEntity<>(service.getEventById(id, cityId), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(value = "/")
    public ResponseEntity<List<EventResponse>> getAllEvents(@PathVariable (value = "cityId") Long cityId) {
        return new ResponseEntity<>(service.getAllEvents(cityId), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable (value = "cityId") Long cityId, @PathVariable("id") long id, @RequestBody @Valid EventRequest event) {
        var result = service.updateEvent(event, id, cityId);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable (value = "cityId") Long cityId, @PathVariable("id") long id) {
        var result = service.deleteEvent(id, cityId);
        if(!result)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.judesys.controllers;

import com.example.judesys.contracts.*;
import com.example.judesys.services.EventService;
import com.example.judesys.services.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities/{cityId}/events/{eventId}/tickets")
@Validated
@AllArgsConstructor
public class TicketController {
    public final TicketService service;

    @CrossOrigin
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<TicketResponse> addTicket(@PathVariable(value = "eventId") Long eventId,
                                                    @PathVariable(value = "cityId") Long cityId,
                                                    @RequestBody @Valid TicketRequest ticketRequest) {
        return new ResponseEntity<>(service.saveTicket(cityId, eventId, ticketRequest), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable("id") long id,
                                                    @PathVariable(value = "eventId") Long eventId,
                                                    @PathVariable(value = "cityId") Long cityId) {
        return new ResponseEntity<>(service.getTicketById(cityId, eventId, id), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(value = "/")
    public ResponseEntity<List<TicketResponse>> getAllTickets(@PathVariable(value = "eventId") Long eventId,
                                                              @PathVariable(value = "cityId") Long cityId) {
        return new ResponseEntity<>(service.getAllTickets(cityId, eventId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/byUser")
    public ResponseEntity<List<TicketDetailsResponse>> getAllTicketsByUser(@PathVariable(value = "eventId") Long eventId,
                                                                           @PathVariable(value = "cityId") Long cityId) {
        return new ResponseEntity<>(service.getAllUserTickets(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable("id") long id,
                                                       @PathVariable(value = "eventId") Long eventId,
                                                       @PathVariable(value = "cityId") Long cityId,
                                                       @RequestBody @Valid TicketRequest ticketRequest) {
        var result = service.updateTicket(cityId, eventId, id, ticketRequest);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") long id,
                                               @PathVariable(value = "eventId") Long eventId,
                                               @PathVariable(value = "cityId") Long cityId) {
        var result = service.deleteTicket(cityId, eventId, id);
        if(!result)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

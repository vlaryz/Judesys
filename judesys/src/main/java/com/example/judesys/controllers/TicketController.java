package com.example.judesys.controllers;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;
import com.example.judesys.contracts.TicketRequest;
import com.example.judesys.contracts.TicketResponse;
import com.example.judesys.services.EventService;
import com.example.judesys.services.TicketService;
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
    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketResponse> updateTicket(@PathVariable("id") long id,
                                                       @PathVariable(value = "eventId") Long eventId,
                                                       @PathVariable(value = "cityId") Long cityId,
                                                       @RequestBody @Valid TicketRequest ticketRequest) {
        return new ResponseEntity<>(service.updateTicket(cityId, eventId, id, ticketRequest), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") long id,
                                               @PathVariable(value = "eventId") Long eventId,
                                               @PathVariable(value = "cityId") Long cityId) {
        service.deleteTicket(cityId, eventId, id);
//        return new ResponseEntity<>("Ticket Deleted Successfully", HttpStatus.OK);
    }
}

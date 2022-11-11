package com.example.judesys.services;

import com.example.judesys.contracts.EventResponse;
import com.example.judesys.contracts.TicketRequest;
import com.example.judesys.contracts.TicketResponse;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.*;
import com.example.judesys.models.Event;
import com.example.judesys.models.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final IEventRepository eventRepository;
    private final ITicketRepository ticketRepository;

    private final ICityRepository cityRepository;

    private final IUserService userService;

    @Override
    public TicketResponse saveTicket(long cityId, long eventId, TicketRequest ticketRequest) {
        Ticket ticket = ticketRequest.getTicket();

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        var userId =  userService.getUser(userName).getId();
        ticket.setCreatedBy(userId);

        var event = eventRepository.findByCityIdAndId(cityId, eventId);
        System.out.println("eventas: " + event.get().getName());
        if(!event.isPresent())
            throw new ResourceNotFoundException("Event", "Id", eventId);

        ticket.setEvent(event.get());
        return TicketResponse.fromTicket(ticketRepository.save(ticket));
    }

    @Override
    public List<TicketResponse> getAllTickets(long cityId, long eventId) {
        return ticketRepository.getTicketsByEventId(eventId).stream().map(TicketResponse::new).toList();
    }

    @Override
    public TicketResponse getTicketById(long cityId, long eventId, long id) {
        if(eventRepository.findByCityIdAndId(cityId, eventId).isPresent()) {
            var ticket = ticketRepository.findById(id);
            if(ticket.isPresent())
                return TicketResponse.fromTicket(ticket.get());
        }
        throw new ResourceNotFoundException("Event", "Id", eventId);
    }

    @Override
    public TicketResponse updateTicket(long cityId, long eventId, long id, TicketRequest ticketRequest) {
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        var userId =  userService.getUser(userName).getId();
        if(existingTicket.getCreatedBy() != userId)
            return null;

        existingTicket.setPrice(ticketRequest.getPrice());
        existingTicket.setType(ticketRequest.getType());
        ticketRepository.save(existingTicket);

        return new TicketResponse(existingTicket);
    }

    @Override
    public boolean deleteTicket(long cityId, long eventId, long id) {
        var ticket = ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        var userId =  userService.getUser(userName).getId();
        if(ticket.getCreatedBy() != userId)
            return false;

        ticketRepository.deleteById(id);
        return true;
    }
}

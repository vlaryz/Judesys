package com.example.judesys.services;

import com.example.judesys.contracts.TicketRequest;
import com.example.judesys.contracts.TicketResponse;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.IEventRepository;
import com.example.judesys.interfaces.ITicketRepository;
import com.example.judesys.interfaces.ITicketService;
import com.example.judesys.models.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final IEventRepository eventRepository;
    private final ITicketRepository ticketRepository;

    @Override
    public TicketResponse saveTicket(TicketRequest ticketRequest, long eventId) {
        Ticket ticket = ticketRequest.getTicket();
        return new TicketResponse(eventRepository.findById(eventId).map(event -> {
            ticket.setEvent(event);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new ResourceNotFoundException("Ticket", "Id", eventId)));
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream().map(TicketResponse::new).toList();
    }

    @Override
    public TicketResponse getTicketById(long id) {
        return new TicketResponse(ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "Id", id)));
    }

    @Override
    public TicketResponse updateTicket(TicketRequest ticketRequest, long id) {
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "Id", id));

        existingTicket.setPrice(ticketRequest.getPrice());
        existingTicket.setType(ticketRequest.getType());
        ticketRepository.save(existingTicket);
        return new TicketResponse(existingTicket);
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket", "Id", id));
        ticketRepository.deleteById(id);
    }
}

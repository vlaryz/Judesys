package com.example.judesys.interfaces;

import com.example.judesys.contracts.TicketRequest;
import com.example.judesys.contracts.TicketResponse;

import java.util.List;

public interface ITicketService {

    TicketResponse saveTicket(TicketRequest ticketRequest, long eventId);

    List<TicketResponse> getAllTickets();

    TicketResponse getTicketById(long id);

    TicketResponse updateTicket(TicketRequest ticketRequest, long id);

    void deleteTicket(long id);
}

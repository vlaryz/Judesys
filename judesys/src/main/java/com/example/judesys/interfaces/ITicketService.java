package com.example.judesys.interfaces;

import com.example.judesys.contracts.TicketRequest;
import com.example.judesys.contracts.TicketResponse;

import java.util.List;

public interface ITicketService {

    TicketResponse saveTicket(long cityId, long eventId, TicketRequest ticketRequest);

    List<TicketResponse> getAllTickets(long cityId, long eventId);

    TicketResponse getTicketById(long cityId, long eventId, long id);

    TicketResponse updateTicket(long cityId, long eventId, long id, TicketRequest ticketRequest);

    void deleteTicket(long cityId, long eventId, long id);
}

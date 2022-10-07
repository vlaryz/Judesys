package com.example.judesys.interfaces;

import com.example.judesys.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getTicketsByEventId(long eventId);
}

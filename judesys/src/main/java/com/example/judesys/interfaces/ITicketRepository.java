package com.example.judesys.interfaces;

import com.example.judesys.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {
}

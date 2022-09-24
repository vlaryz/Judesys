package com.example.judesys.models;

import com.example.judesys.models.enums.TicketType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "type", nullable = false)
    private TicketType type;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}

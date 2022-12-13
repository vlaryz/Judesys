package com.example.judesys.models;

import com.example.judesys.models.enums.TicketType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "type", nullable = false)
    private TicketType type;

    @Column(name = "createdBy")
    private long createdBy;

    @Column(name = "boughtBy")
    private Long boughtBy;

    @ManyToOne()
    @JoinColumn(name = "fk_eventId", nullable = false)
    private Event event;

    public Ticket(long price, TicketType type, long boughtBy) {
        this.price = price;
        this.type = type;
        this.boughtBy = boughtBy;
    }

    public Ticket(long price, TicketType type) {
        this.price = price;
        this.type = type;
    }
}

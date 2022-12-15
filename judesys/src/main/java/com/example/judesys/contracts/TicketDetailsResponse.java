package com.example.judesys.contracts;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import com.example.judesys.models.Ticket;
import com.example.judesys.models.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetailsResponse {
    private long id;
    private long price;
    private TicketType type;
    private long cityId;
    private long eventId;

    private String city;
    private String event;

    private Long boughtBy;

    public TicketDetailsResponse(Ticket ticket, City city, Event event) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.type = ticket.getType();
        this.boughtBy = ticket.getBoughtBy();
        this.cityId = city.getId();
        this.eventId = event.getId();
        this.city = city.getName();
        this.event = event.getName();
    }
    public static TicketResponse fromTicket (Ticket ticket){
        return new TicketResponse(ticket.getId(), ticket.getPrice(), ticket.getType(), ticket.getBoughtBy());
    }
}

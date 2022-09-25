package com.example.judesys.contracts;

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
public class TicketResponse {
    private long price;
    private TicketType type;

    public TicketResponse(Ticket ticket) {
        this.price = ticket.getPrice();
        this.type = ticket.getType();
    }
    public static TicketResponse fromTicket (Ticket ticket){
        return new TicketResponse(ticket.getPrice(), ticket.getType());
    }
}

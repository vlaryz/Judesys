package com.example.judesys.contracts;

import com.example.judesys.models.Event;
import com.example.judesys.models.Ticket;
import com.example.judesys.models.enums.TicketType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketRequest {
    private long price;
    private TicketType type;


    public Ticket getTicket() {
        return new Ticket(this.price, this.type);
    }
}

package com.example.judesys.contracts;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import com.example.judesys.models.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventRequest {

    private String name;
    private boolean isFree;
    private String shortDescription;
    private String longDescription;

    public Event getEvent() {
        return new Event(this.name, this.isFree, this.shortDescription, this.longDescription);
    }
}

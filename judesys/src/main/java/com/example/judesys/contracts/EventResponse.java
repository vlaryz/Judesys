package com.example.judesys.contracts;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private String name;
    private boolean isFree;

    public EventResponse(Event event) {
        this.name = event.getName();
        this.isFree = event.isFree();
    }
    public static EventResponse fromEvent (Event event){
        return new EventResponse(event.getName(), event.isFree());
    }
}

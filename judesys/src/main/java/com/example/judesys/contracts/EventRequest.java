package com.example.judesys.contracts;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import com.example.judesys.models.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EventRequest {

    private String name;
//    @JsonProperty("isFree")
    private String isFree;
    private String shortDescription;
    private String longDescription;

//    public boolean getIsFree() {
//        return this.isFree;
//    }

    public Event getEvent() {
        var free = this.isFree.contains("true") ? true : false;
        return new Event(this.name, free, this.shortDescription, this.longDescription);
    }
}

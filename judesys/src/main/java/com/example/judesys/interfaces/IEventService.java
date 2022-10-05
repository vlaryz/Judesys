package com.example.judesys.interfaces;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;

import java.util.List;

public interface IEventService {

    EventResponse saveEvent(EventRequest eventRequest, long cityId);

    List<EventResponse> getAllEvents(long cityId);

    EventResponse getEventById(long id, long cityId);

    EventResponse updateEvent(EventRequest eventRequest, long id, long cityId);

    long deleteEvent(long id, long cityId);
}

package com.example.judesys.interfaces;

import com.example.judesys.contracts.CityRequest;
import com.example.judesys.contracts.CityResponse;
import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;

import java.util.List;

public interface IEventService {

    EventResponse saveEvent(EventRequest eventRequest, long cityId);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(long id);

    EventResponse updateEvent(EventRequest eventRequest, long id);

    void deleteEvent(long id);
}

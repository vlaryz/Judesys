package com.example.judesys.services;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.ICityRepository;
import com.example.judesys.interfaces.IEventRepository;
import com.example.judesys.interfaces.IEventService;
import com.example.judesys.models.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final ICityRepository cityRepository;

    @Override
    public EventResponse saveEvent(EventRequest eventRequest, long cityId) {
        Event event = eventRequest.getEvent();
        return new EventResponse(cityRepository.findById(cityId).map(city -> {
            event.setCity(city);
            return eventRepository.save(event);
        }).orElseThrow(() -> new ResourceNotFoundException("Event", "Id", cityId)));
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream().map(EventResponse::new).toList();
    }

    @Override
    public EventResponse getEventById(long id) {
        return new EventResponse(eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event", "Id", id)));
    }

    @Override
    public EventResponse updateEvent(EventRequest event, long id) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "Id", id));

        existingEvent.setName(event.getName());
        eventRepository.save(existingEvent);
        return new EventResponse(existingEvent);
    }

    @Override
    public void deleteEvent(long id) {
        eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event", "Id", id));
        eventRepository.deleteById(id);
    }
}

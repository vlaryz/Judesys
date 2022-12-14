package com.example.judesys.services;

import com.example.judesys.contracts.EventRequest;
import com.example.judesys.contracts.EventResponse;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.ICityRepository;
import com.example.judesys.interfaces.IEventRepository;
import com.example.judesys.interfaces.IEventService;
import com.example.judesys.interfaces.IUserService;
import com.example.judesys.models.Event;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final ICityRepository cityRepository;

    private final IUserService userService;

    @Override
    public EventResponse saveEvent(EventRequest eventRequest, long cityId) {
        System.out.println(eventRequest);
        Event event = eventRequest.getEvent();
        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        var userId =  userService.getUser(userName).getId();
        event.setCreatedBy(userId);

        return new EventResponse(cityRepository.findById(cityId).map(city -> {
            event.setCity(city);
            return eventRepository.save(event);
        }).orElseThrow(() -> new ResourceNotFoundException("Event", "Id", cityId)));
    }

    @Override
    public List<EventResponse> getAllEvents(long cityId) {
        return eventRepository.findAllByCityId(cityId).stream()
                .map(EventResponse::new).toList();
    }

    @Override
    public EventResponse getEventById(long id, long cityId) {
        var event = eventRepository.findByCityIdAndId(cityId, id);
        if(event.isPresent())
            return EventResponse.fromEvent(event.get());

        throw new ResourceNotFoundException("City", "Id", cityId);
    }

    @Override
    public EventResponse updateEvent(EventRequest event, long id, long cityId) {
        var existingEvent = eventRepository.findByCityIdAndId(cityId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        if(userService.getUser(userName).getId() != existingEvent.getCreatedBy())
            return null;

        existingEvent.setName(event.getEvent().getName());
        existingEvent.setFree(event.getEvent().isFree());
        eventRepository.save(existingEvent);
        return new EventResponse(existingEvent);
    }

    @Override
    public boolean deleteEvent(long id, long cityId) {
        var event = eventRepository.findByCityIdAndId(cityId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        if(userService.getUser(userName).getId() != event.getCreatedBy())
            return false;

        eventRepository.deleteByCityIdAndId(cityId, id);
        return true;
    }
}

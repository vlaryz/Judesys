package com.example.judesys.services;

import com.example.judesys.contracts.CityRequest;
import com.example.judesys.contracts.CityResponse;
import com.example.judesys.exceptions.BadRequestParamsException;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.ICityRepository;
import com.example.judesys.interfaces.ICityService;
import com.example.judesys.interfaces.IUserService;
import com.example.judesys.models.City;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService implements ICityService {

    private final ICityRepository repository;
    private final IUserService userService;

    @Override
    public CityResponse saveCity(CityRequest city) {
        for (City x : repository.findAll()) {
            if(x.getName().equals(city.getName()))
                try {
                    throw new BadRequestParamsException("City already exists");
                } catch (BadRequestParamsException e) {
                    throw new RuntimeException(e);
                }
        }

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        var userId =  userService.getUser(userName).getId();
        var savedCity = city.getCity();
        savedCity.setCreatedBy(userId);

        return new CityResponse(repository.save(savedCity));
    }

    @Override
    public List<CityResponse> getAllCities() {
        return repository.findAll().stream().map(CityResponse::new).toList();
    }

    @Override
    public CityResponse geCityById(long id) {
//        System.out.println(repository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("City", "Id", id)));
        return new CityResponse(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id)));
    }

    @Override
    public CityResponse updateCity(CityRequest city, long id) {
//        System.out.println(city.toString());

        City existingCity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("City", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        if(userService.getUser(userName).getId() != existingCity.getCreatedBy())
           return null;

        existingCity.setName(city.getName());
        repository.save(existingCity);
        return new CityResponse(existingCity);
    }

    @Override
    public boolean deleteCity(long id) {
        var city = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));

        var userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().trim();
        if(userService.getUser(userName).getId() != city.getCreatedBy())
            return false;

        repository.deleteById(id);
        return true;
    }
}

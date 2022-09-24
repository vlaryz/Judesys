package com.example.judesys.services;

import com.example.judesys.contracts.CityRequest;
import com.example.judesys.contracts.CityResponse;
import com.example.judesys.exceptions.BadRequestParamsException;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.ICityRepository;
import com.example.judesys.interfaces.ICityService;
import com.example.judesys.models.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService implements ICityService {

    private final ICityRepository repository;

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
        return new CityResponse(repository.save((city.getCity())));
    }

    @Override
    public List<CityResponse> getAllCities() {
        return null;
    }

    @Override
    public CityResponse geCityById(long id) {
        return new CityResponse(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id)));
    }

    @Override
    public CityResponse updateCity(CityRequest city, long id) {
        System.out.println(city.toString());
        City existingCity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("City", "Id", id));

        existingCity.setName(city.getName());
        repository.save(existingCity);
        return new CityResponse(existingCity);
    }

    @Override
    public void deleteCity(long id) {
        repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
        repository.deleteById(id);
    }
}

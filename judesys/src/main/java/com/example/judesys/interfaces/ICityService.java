package com.example.judesys.interfaces;

import com.example.judesys.contracts.CityRequest;
import com.example.judesys.contracts.CityResponse;

import java.util.List;

public interface ICityService {

    CityResponse saveCity(CityRequest city);

    List<CityResponse> getAllCities();

    CityResponse geCityById(long id);

    CityResponse updateCity(CityRequest city, long id);

    boolean deleteCity(long id);
}

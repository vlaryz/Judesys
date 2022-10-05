package com.example.judesys.contracts;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
    private long id;
    private String name;

    public CityResponse(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
    public static CityResponse fromCity (City city){
        return new CityResponse(city);
    }
}

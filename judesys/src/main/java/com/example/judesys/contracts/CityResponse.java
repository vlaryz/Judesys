package com.example.judesys.contracts;

import com.example.judesys.models.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
    private String name;

    public CityResponse(City city) {
        this.name = city.getName();
    }
    public static CityResponse fromCustomer (City customer){
        return new CityResponse(customer.getName());
    }
}

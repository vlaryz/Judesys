package com.example.judesys.contracts;

import com.example.judesys.models.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
public class CityRequest {

    @NotBlank(message = "City not provided")
    private String name;

    public City getCity() {
        return new City(this.name);
    }
}

package com.example.judesys.interfaces;

import com.example.judesys.models.City;
import com.example.judesys.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICityRepository extends JpaRepository<City, Long> {
}

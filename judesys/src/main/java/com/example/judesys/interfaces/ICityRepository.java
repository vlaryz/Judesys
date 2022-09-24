package com.example.judesys.interfaces;

import com.example.judesys.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Long> {
}

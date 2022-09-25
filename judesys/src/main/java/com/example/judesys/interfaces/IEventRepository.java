package com.example.judesys.interfaces;

import com.example.judesys.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Long> {
}

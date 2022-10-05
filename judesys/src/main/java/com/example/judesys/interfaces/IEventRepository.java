package com.example.judesys.interfaces;

import com.example.judesys.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByCityIdAndId(long cityId, long id);
    List<Event> findAllByCityId(long cityId);
    Long deleteByCityIdAndId(long cityId, long id);
}

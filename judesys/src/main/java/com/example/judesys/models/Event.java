package com.example.judesys.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_free", nullable = false)
    private boolean isFree;

    @OneToMany(mappedBy="event")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}

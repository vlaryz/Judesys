package com.example.judesys.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column(name = "shortDesrc", nullable = false)
    private String shortDesription;
    @Column(name = "longDesrc", nullable = false)
    private String longDesription;
    @OneToMany(mappedBy="event")
    private List<Ticket> tickets;

    @Column(name = "createdBy")
    private long createdBy;

    @ManyToOne()
    @JoinColumn(name = "fk_cityId", nullable = false)
    private City city;

    public Event(String name, boolean isFree, String shortDesription, String longDesription) {
        this.name = name;
        this.isFree = isFree;
        this.shortDesription = shortDesription;
        this.longDesription = longDesription;
    }
    public Event() {

    }
}

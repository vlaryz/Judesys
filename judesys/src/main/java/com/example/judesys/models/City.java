package com.example.judesys.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createdBy")
    private long createdBy;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Event> events;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

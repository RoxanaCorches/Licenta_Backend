package com.example.backend_springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "apartments")
public class Apartment implements Serializable {
    @Id
    @Column(name = "id_apartment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idApartment;

    //@Column(name = "id_owner")
    //private int idOwner;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_owner", nullable = false)
    private User user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Apartment() {}
}

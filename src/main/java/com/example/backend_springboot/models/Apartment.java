package com.example.backend_springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "apartments")
public class Apartment implements Serializable {
    @Id
    @Column(name = "id_apartment")
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID idApartment;

    //@Column(name = "id_owner")
    //private int idOwner;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_owner", nullable = false)
    private User user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    //@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    //private List<Review> reviews;

    public Apartment() {}

}

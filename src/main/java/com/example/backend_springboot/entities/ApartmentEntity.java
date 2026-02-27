package com.example.backend_springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "apartments")
public class ApartmentEntity implements Serializable {
    @Id
    @Column(name = "id_apartment")
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID idApartment;

    //@Column(name = "id_owner")
    //private int idOwner;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "area")
    private Integer area;

    @Column(name = "country")
    private String country;

    @Column(name = "floor")
    private String floor;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "pets_allowed")
    private Boolean petsAllowed;

    @Column(name = "smoking_allowed")
    private Boolean smokingAllowed;

    @Column(name = "parties_allowed")
    private Boolean partiesAllowed;

    @Column(name = "checkIn")
    private LocalDate checkIn;

    @Column(name = "checkOut")
    private LocalDate checkOut;

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "images_folder_url")
    private String imagesFolderUrl;
/*
    @Column(name = "amenities")
    private List<String> amenities;
*/
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_owner", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<RentalEntity> rentals;

    //@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    //private List<Review> reviews;

    public ApartmentEntity() {}

}

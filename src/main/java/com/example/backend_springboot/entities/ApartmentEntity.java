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

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name = "country")
    private String country;

    @Column(name = "floor")
    private String floor;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private Integer zipcode;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "tv", nullable = false)
    private boolean tv = false;

    @Column(name = "wifi", nullable = false)
    private boolean wifi = false;

    @Column(name = "kitchen", nullable = false)
    private boolean kitchen =  false;

    @Column(name = "washer", nullable = false)
    private boolean washer =  false;

    @Column(name = "airConditioning", nullable = false)
    private boolean air_conditioning =  false;

    @Column(name = "pool", nullable = false)
    private boolean pool =  false;

    @Column(name = "hotTub", nullable = false)
    private boolean hot_tub =  false;

    @Column(name = "bbqGrill", nullable = false)
    private boolean BBQ_grill =  false;

    @Column(name = "poolTable", nullable = false)
    private boolean pool_table =  false;

    @Column(name = "indoorFireplace", nullable = false)
    private boolean indoor_fireplace =  false;

    @Column(name = "piano", nullable = false)
    private boolean piano =  false;

    @Column(name = "balcony", nullable = false)
    private boolean balcony =  false;

    @Column(name = "terrace", nullable = false)
    private boolean terrace =  false;

    @Column(name = "gardenView", nullable = false)
    private boolean garden_view =  false;

    @Column(name = "skiOut", nullable = false)
    private boolean ski_out =  false;

    @Column(name = "lakeAccess", nullable = false)
    private boolean lake_access =  false;

    @Column(name = "beachAccess", nullable = false)
    private boolean beach_access =  false;

    @Column(name = "pets_allowed", nullable = false)
    private boolean petsAllowed =  false;

    @Column(name = "smoking_allowed", nullable = false)
    private boolean smokingAllowed =  false;

    @Column(name = "parties_allowed", nullable = false)
    private boolean partiesAllowed =  false;

    @Column(name = "checkIn")
    private LocalDate checkIn;

    @Column(name = "checkOut")
    private LocalDate checkOut;

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

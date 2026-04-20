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
import java.time.LocalTime;
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

    @Column(name = "air_Conditioning", nullable = false)
    private boolean airConditioning =  false;

    @Column(name = "pool", nullable = false)
    private boolean pool =  false;

    @Column(name = "hotTub", nullable = false)
    private boolean hotTub =  false;

    @Column(name = "bbq_Grill", nullable = false)
    private boolean bbqGrill =  false;

    @Column(name = "pool_table", nullable = false)
    private boolean poolTable =  false;

    @Column(name = "indoor_fireplace", nullable = false)
    private boolean indoorFireplace =  false;

    @Column(name = "piano", nullable = false)
    private boolean piano =  false;

    @Column(name = "balcony", nullable = false)
    private boolean balcony =  false;

    @Column(name = "terrace", nullable = false)
    private boolean terrace =  false;

    @Column(name = "garden_view", nullable = false)
    private boolean gardenView =  false;

    @Column(name = "ski_out", nullable = false)
    private boolean skiOut =  false;

    @Column(name = "lake_access", nullable = false)
    private boolean lakeAccess =  false;

    @Column(name = "beach_access", nullable = false)
    private boolean beachAccess =  false;

    @Column(name = "pets_allowed", nullable = false)
    private boolean petsAllowed =  false;

    @Column(name = "smoking_allowed", nullable = false)
    private boolean smokingAllowed =  false;

    @Column(name = "parties_allowed", nullable = false)
    private boolean partiesAllowed =  false;

    @Column(name = "check_in_from")
    private LocalTime checkInFrom;

    @Column(name = "check_in_until")
    private LocalTime checkInUntil;

    @Column(name = "check_out_from")
    private LocalTime checkOutFrom;

    @Column(name = "check_out_until")
    private LocalTime checkOutUntil;

    @Column(name = "availability")
    private boolean availability = true;

    @Column(name = "image_main")
    private String imageMain;

    @Lob
    @Column(name = "image_1", columnDefinition = "MEDIUMBLOB")
    private byte[] image1;

    @Lob
    @Column(name = "image_2", columnDefinition = "MEDIUMBLOB")
    private byte[] image2;

    @Lob
    @Column(name = "image_3", columnDefinition = "MEDIUMBLOB")
    private byte[] image3;

    @Lob
    @Column(name = "image_4", columnDefinition = "MEDIUMBLOB")
    private byte[] image4;;

    @Column(name = "metadata_url")
    private String metadataUrl;

    @Column(name = "token_id")
    private String tokenId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_owner", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<RentalEntity> rentals;

    public ApartmentEntity() {}

    public void setHotTub(boolean hotTub) {
    }
}

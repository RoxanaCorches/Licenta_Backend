package com.example.backend_springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="rentals")
public class Rental implements Serializable {
    @Id
    @Column(name = "id_rental")
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID idRental;

    //@Column(name = "id_renter")
    //private int idRenter;

    //@Column(name = "id_apartment")
    //private UUID idApartment;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "transaction_hash")
    private Double transactionHash;

    @ManyToOne
    @JoinColumn(name = "id_renter", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_apartment", nullable = false)
    private Apartment apartment;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
    private Review review;


    public Rental(){}

}

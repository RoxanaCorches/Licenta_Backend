package com.example.backend_springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="rentals")
public class Rental implements Serializable {
    @Id
    @Column(name = "id_rental")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRental;

    //@Column(name = "id_renter")
    //private int idRenter;

    @Column(name = "id_apartment")
    private int idApartment;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "transaction_hash")
    private Double transactionHash;

    @ManyToOne
    @JoinColumn(name = "id_renter", nullable = false)
    private User user;

    public Rental(){}

}

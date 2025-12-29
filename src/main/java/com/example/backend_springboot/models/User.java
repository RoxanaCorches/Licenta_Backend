package com.example.backend_springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "blockchain_address")
    private String blockchainAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;



    public User() {}

}

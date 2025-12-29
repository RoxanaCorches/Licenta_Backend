package com.example.backend_springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReview;

   // @Column(name = "id_user")
    //private int idUser;

   // @Column(name = "id_apartment")
    //private int idApartment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private Double rating;

   @ManyToOne
   @JoinColumn(name = "id_user")
   private User user;

   @ManyToOne
   @JoinColumn(name = "id_apartment")
   private Apartment apartment;

    public Review() {}
}

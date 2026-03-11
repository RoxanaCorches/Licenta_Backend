package com.example.backend_springboot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class ReviewEntity implements Serializable {
    @Id
    @Column(name = "id_review")
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID idReview;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private LocalDate date;

    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private Double rating;

   @ManyToOne
   @JoinColumn(name = "id_user", nullable = false)
   private UserEntity user;

   @OneToOne
   @JoinColumn(name = "id_rental", nullable = false)
   private RentalEntity rental;

   public ReviewEntity() {}
}

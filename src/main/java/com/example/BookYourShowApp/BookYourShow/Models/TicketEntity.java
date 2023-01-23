package com.example.BookYourShowApp.BookYourShow.Models;
import jakarta.persistence.*;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Tickets")
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alloted_seats;

    private double amount;

    private Date bookedAt;


    //Parent is User
    //Many tickets have one user
    //Many-to-one Relation
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeatsEntity> bookedSeats;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

}

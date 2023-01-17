package com.example.BookYourShowApp.BookYourShow.Models;

import com.example.BookYourShowApp.BookYourShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="show_seats")
@Data
@NoArgsConstructor
public class ShowSeatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean isAvailable;

    private boolean isBooked;

    private Date bookAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;

}

package com.example.BookYourShowApp.BookYourShow.Models;

import com.example.BookYourShowApp.BookYourShow.Enums.SeatType;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="show_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowSeatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean Booked;

    private Date bookAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;

}

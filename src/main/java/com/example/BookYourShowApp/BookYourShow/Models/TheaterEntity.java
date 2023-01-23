package com.example.BookYourShowApp.BookYourShow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name="Theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

    //list of shows
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows;


    //For each theater they have many theater seats
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntityList;
}

package com.example.BookYourShowApp.BookYourShow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int duration;

    @Column(nullable = false)
    private String movieName;

    private Date releaseDate;

    //Each Movie can have many shows
    //List of Shows
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<ShowEntity> showAll;
}

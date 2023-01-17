package com.example.BookYourShowApp.BookYourShow.RequestDtos;


import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class MovieRequestDto {

    @Column(nullable = false)
    private String movieName;

    private Date releaseDate;

    private int duration;
}

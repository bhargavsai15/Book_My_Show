package com.example.BookYourShowApp.BookYourShow.RequestDtos;


import lombok.Data;

import java.sql.Date;

@Data
public class MovieRequestDto{

    private String movieName;

    private Date releaseDate;

    private int duration;
}

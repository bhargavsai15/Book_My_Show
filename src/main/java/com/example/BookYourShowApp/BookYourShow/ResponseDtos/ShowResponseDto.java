package com.example.BookYourShowApp.BookYourShow.ResponseDtos;


import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShowResponseDto {

    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    private int movieId;

    private String movieName;

    private int theaterId;

    private String theaterName;

}

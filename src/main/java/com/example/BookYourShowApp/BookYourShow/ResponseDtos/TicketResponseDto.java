package com.example.BookYourShowApp.BookYourShow.ResponseDtos;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TicketResponseDto {

    private int id;

    private String alloted_seats;

    private double amount;

    private Date bookedAt;

    private int userId;

    private String userName;

    private int showId;

    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;
}

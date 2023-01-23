package com.example.BookYourShowApp.BookYourShow.ResponseDtos;

import com.example.BookYourShowApp.BookYourShow.Enums.SeatType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class UserResponseDto1 {


    private String userName;

    private String movieName;

    private String  theaterName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String allotedSeats;

    private List<String> seatType;

    private List<String> seatNo;

}

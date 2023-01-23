package com.example.BookYourShowApp.BookYourShow.RequestDtos;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private int movieId;

    private int theaterId;

    private double multiplier;
}

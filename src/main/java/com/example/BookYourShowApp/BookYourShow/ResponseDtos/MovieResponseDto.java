package com.example.BookYourShowApp.BookYourShow.ResponseDtos;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MovieResponseDto {

    private int id;

    private int duration;

    private String movieName;

    private Date releaseDate;

    List<ShowResponseDto> showAll;
}

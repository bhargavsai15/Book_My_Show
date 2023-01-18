package com.example.BookYourShowApp.BookYourShow.ResponseDtos;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterSeatEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;


@Builder
@Data
@AllArgsConstructor
public class TheaterResponseDto {

    private int id;

    private String name;

    private String city;

    private String address;

    private List<ShowResponseDto> listOfShows;
//
//    private List<TheaterSeatEntity> theaterSeatEntityList;
}

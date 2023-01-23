package com.example.BookYourShowApp.BookYourShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TheaterResponseDto1 {
    private int id;

    private String name;

    private String city;

    private String address;

}

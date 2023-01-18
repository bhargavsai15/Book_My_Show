package com.example.BookYourShowApp.BookYourShow.Convertors;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TheaterRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.ShowResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterConvertor {

    public static TheaterEntity convertTheaterRequestToEntity(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater=TheaterEntity.builder().
                                    name(theaterRequestDto.getName()).
                                    address(theaterRequestDto.getAddress()).
                                    city(theaterRequestDto.getCity()).build();
        return theater;
    }

    public static TheaterResponseDto convertDtoToEntity(TheaterEntity theater){
        TheaterResponseDto theaterResponseDto=TheaterResponseDto.builder().
                                                id(theater.getId()).
                                                name(theater.getName()).
                                                address(theater.getAddress()).
                                                city(theater.getCity()).build();

        List<ShowResponseDto> showResponseDtos=new ArrayList<>();

        for(ShowEntity showEntity:theater.getListOfShows()){
            ShowResponseDto showResponseDto=ShowResponseDto.builder().
                                                id(showEntity.getId()).
                                                showDate(showEntity.getShowDate()).
                                                showTime(showEntity.getShowTime()).build();
            showResponseDtos.add(showResponseDto);
        }

        theaterResponseDto.setListOfShows(showResponseDtos);

        return theaterResponseDto;
    }
}

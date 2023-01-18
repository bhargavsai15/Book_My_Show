package com.example.BookYourShowApp.BookYourShow.Convertors;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;

public class ShowConvertor {

    public static ShowEntity convertShowRequestDtoToEntity(ShowRequestDto showRequestDto){
        ShowEntity showEntity=ShowEntity.builder().
                                showDate(showRequestDto.getShowDate()).
                                showTime(showRequestDto.getShowTime()).
                                multiplier(showRequestDto.getMultiplier()).build();
        return showEntity;
    }
}

package com.example.BookYourShowApp.BookYourShow.Convertors;


import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import lombok.Data;


@Data
public class UserConvertor {

    public static UserEntity convertUserRequestToEntity(UserRequestDto userRequestDto){
        UserEntity user=  UserEntity.builder().
                name(userRequestDto.getName()).
                mobile(userRequestDto.getMobile()).build();

        return user;
    }
}

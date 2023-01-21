package com.example.BookYourShowApp.BookYourShow.Convertors;


import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserConvertor {

    public static UserEntity convertUserRequestToEntity(UserRequestDto userRequestDto) {
        UserEntity user = UserEntity.builder().
                name(userRequestDto.getName()).
                mobile(userRequestDto.getMobile()).build();
        return user;
    }

    public static UserResponseDto convertUserEntityToResponseDto(UserEntity user) {
        UserResponseDto userResponseDto = UserResponseDto.builder().
                id(user.getId()).
                name(user.getName()).
                mobile(user.getMobile()).build();

//        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
//        for (TicketEntity ticket : user.getListOfTickets()) {
//            TicketResponseDto ticketResponseDto = TicketResponseDto.builder().
//                    id(ticket.getId()).
//                    alloted_seats(ticket.getAlloted_seats()).
//                    bookedAt(ticket.getBookedAt()).
//                    amount(ticket.getAmount()).build();
//
//            UserEntity user3 = ticket.getUser();
//            UserEntity user1 = UserEntity.builder().
//                    id(user3.getId()).
//                    name(user3.getName()).build();
//
//            ShowEntity show = ticket.getShow();
//            ShowEntity show1 = ShowEntity.builder().
//                    id(show.getId()).
//                    showDate(show.getShowDate()).
//                    showTime(show.getShowTime()).build();
//            ticketResponseDto.setUser(user1);
//            ticketResponseDto.setShow(show1);
//            ticketResponseDtoList.add(ticketResponseDto);
//        }
//        userResponseDto.setListOfTickets(ticketResponseDtoList);
        return userResponseDto;
    }

    public static List<UserResponseDto> convertListOfUserEntityToResponseDto(List<UserEntity> userEntities) {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for (UserEntity user : userEntities) {
            UserResponseDto userResponseDto = UserResponseDto.builder().
                    id(user.getId()).
                    name(user.getName()).
                    mobile(user.getMobile()).build();

//            List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
//
//            for (TicketEntity ticket : user.getListOfTickets()) {
//                TicketResponseDto ticketResponseDto = TicketResponseDto.builder().
//                        id(ticket.getId()).
//                        alloted_seats(ticket.getAlloted_seats()).
//                        bookedAt(ticket.getBookedAt()).
//                        amount(ticket.getAmount()).build();
//
//                UserEntity user3 = ticket.getUser();
//                UserEntity user1 = UserEntity.builder().
//                        id(user3.getId()).
//                        name(user3.getName()).build();
//
//                ShowEntity show = ticket.getShow();
//                ShowEntity show1 = ShowEntity.builder().
//                        id(show.getId()).
//                        showDate(show.getShowDate()).
//                        showTime(show.getShowTime()).build();
//
//                ticketResponseDto.setUser(user1);
//                ticketResponseDto.setShow(show1);
//                ticketResponseDtoList.add(ticketResponseDto);
//            }
//            userResponseDto.setListOfTickets(ticketResponseDtoList);
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }
}

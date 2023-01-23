package com.example.BookYourShowApp.BookYourShow.Convertors;


import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TheaterRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto;
import org.apache.catalina.User;

import java.util.*;

public class TicketConvertor {

    public static List<TicketResponseDto> convertRequestDtoToEntity(List<TicketEntity> ticketEntities){

        List<TicketResponseDto> ticketResponseDtoList=new ArrayList<>();

        for(TicketEntity ticket:ticketEntities){
            TicketResponseDto ticketResponseDto=TicketResponseDto.builder().
                                                        id(ticket.getId()).
                                                        alloted_seats(ticket.getAlloted_seats()).
                                                        bookedAt(ticket.getBookedAt()).
                                                        amount(ticket.getAmount()).
                                                        userId(ticket.getUser().getId()).
                                                        userName(ticket.getUser().getName()).
                                                        showId(ticket.getShow().getId()).
                                                        showDate(ticket.getShow().getShowDate()).
                                                        showTime(ticket.getShow().getShowTime()).
                                                        movieName(ticket.getShow().getMovie().getMovieName()).build();

            ticketResponseDtoList.add(ticketResponseDto);
        }
        return ticketResponseDtoList;
    }

//    public static List<TheaterRequestDto> convertUserEntityTOTheaterResponseDto(UserEntity user){
//        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
//
//        for (TicketEntity ticket : user.getListOfTickets()) {
//
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
//
//            ticketResponseDto.setUser(user1);
//            ticketResponseDto.setShow(show1);
//            ticketResponseDtoList.add(ticketResponseDto);
//        }
//        userResponseDto.setListOfTickets(ticketResponseDtoList);
//        userResponseDtoList.add(userResponseDto);
//
//    }
}

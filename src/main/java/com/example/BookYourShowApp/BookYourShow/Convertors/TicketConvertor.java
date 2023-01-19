package com.example.BookYourShowApp.BookYourShow.Convertors;


import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto;
import java.util.*;

public class TicketConvertor {

    public static List<TicketResponseDto> convertRequestDtoToEntity(List<TicketEntity> ticketEntities){

        List<TicketResponseDto> ticketResponseDtoList=new ArrayList<>();

        for(TicketEntity ticket:ticketEntities){
            TicketResponseDto ticketResponseDto=TicketResponseDto.builder().
                                                        id(ticket.getId()).
                                                        alloted_seats(ticket.getAlloted_seats()).
                                                        bookedAt(ticket.getBookedAt()).
                                                        amount(ticket.getAmount()).build();

            UserEntity user=ticket.getUser();
            UserEntity user1=UserEntity.builder().
                                    id(user.getId()).
                                    name(user.getName()).build();

            ShowEntity show=ticket.getShow();
            ShowEntity show1=ShowEntity.builder().
                                    id(show.getId()).
                                    showDate(show.getShowDate()).
                                    showTime(show.getShowTime()).build();
            ticketResponseDto.setUser(user1);
            ticketResponseDto.setShow(show1);
            ticketResponseDtoList.add(ticketResponseDto);
        }
        return ticketResponseDtoList;
    }
}

package com.example.BookYourShowApp.BookYourShow.RequestDtos;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {

    private String name;

    private String mobile;

//    private List<TicketEntity> listOfTickets;

}

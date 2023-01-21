package com.example.BookYourShowApp.BookYourShow.ResponseDtos;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private int id;

    private String name;

    private String mobile;

//    private List<TicketResponseDto> listOfTickets;

}

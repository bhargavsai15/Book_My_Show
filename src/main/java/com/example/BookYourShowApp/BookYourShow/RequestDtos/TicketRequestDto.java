package com.example.BookYourShowApp.BookYourShow.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
public class TicketRequestDto {

    private List<String> requestedSeats;

    private int showId;

    private int userId;
}

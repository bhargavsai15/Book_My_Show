package com.example.BookYourShowApp.BookYourShow.Controllers;

import com.example.BookYourShowApp.BookYourShow.RequestDtos.TicketRequestDto;
import com.example.BookYourShowApp.BookYourShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody()TicketRequestDto ticketRequestDto){
        String response= null;
        try {
            response = ticketService.bookTicket(ticketRequestDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Requested are seats not available",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

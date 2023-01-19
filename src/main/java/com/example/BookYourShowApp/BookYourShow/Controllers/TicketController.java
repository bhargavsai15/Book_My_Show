package com.example.BookYourShowApp.BookYourShow.Controllers;

import com.example.BookYourShowApp.BookYourShow.RequestDtos.TicketRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto;
import com.example.BookYourShowApp.BookYourShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets(){
        List<TicketResponseDto> ticketResponseDtos=ticketService.getAllTickets();
        return new ResponseEntity<>(ticketResponseDtos,HttpStatus.FOUND);
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id")Integer id){
        String response=ticketService.deleteTicket(id);
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}

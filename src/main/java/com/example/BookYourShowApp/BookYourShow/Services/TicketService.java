package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.TicketRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.UserRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(TicketRequestDto ticketRequestDto) throws Exception{

        List<String> requestedSeats=ticketRequestDto.getRequestedSeats();

        UserEntity user=userRepository.findById(ticketRequestDto.getUserId()).get();

        ShowEntity show=showRepository.findById(ticketRequestDto.getShowId()).get();


        //Get the show seats from show entity
        List<ShowSeatsEntity> showSeatsEntityList=show.getShowSeatsEntityList();



        List<ShowSeatsEntity> bookedSeats=new ArrayList<>();


        //Validation
        for(ShowSeatsEntity showSeats:showSeatsEntityList){
            String seatNo=showSeats.getSeatNo();

            if(showSeats.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeats);
            }
        }

        //FAILURE
        if(bookedSeats.size()!= requestedSeats.size()){
            throw new Exception("Requested seats are not available");
        }

        double multiplier=show.getMultiplier();

        double totalAmount=0;
        int rate=0;

        StringBuilder seatsBooked= new StringBuilder();

        //SUCCESS
        TicketEntity ticket=new TicketEntity();

        for(ShowSeatsEntity bookedSeat:bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookAt(new Date());
            bookedSeat.setShow(show);
            bookedSeat.setTicket(ticket);

            String seatNo=bookedSeat.getSeatNo();

            seatsBooked.append(seatNo).append(",");

            if(seatNo.charAt(0)=='1'){
                rate=100;
            }else {
                rate=200;
            }
            totalAmount=totalAmount+multiplier*rate;
        }

        ticket.setBookedAt(new Date());
        ticket.setShow(show);
        ticket.setAmount(totalAmount);
        ticket.setUser(user);
        ticket.setAlloted_seats(seatsBooked.toString());
        ticket.setBookedSeats(bookedSeats);

        ticketRepository.save(ticket);
        return "Booked Successfully";
    }
}

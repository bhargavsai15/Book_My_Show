package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Convertors.TicketConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.TicketRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.UserRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TheaterRequestDto;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TicketRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TicketResponseDto1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static aj.org.objectweb.asm.Opcodes.NULL;

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


    public List<TicketResponseDto> getAllTickets(){
        List<TicketEntity> ticketEntities=ticketRepository.findAll();
        List<TicketResponseDto> ticketResponseDtoList= TicketConvertor.convertRequestDtoToEntity(ticketEntities);
        return ticketResponseDtoList;
    }

    public TicketResponseDto1 deleteTicket(int id){

        TicketResponseDto1 ticketResponseDto1=new TicketResponseDto1();
        double cancle_fee=20; //20% of money will be deduced
        int totalAmount=0;
        boolean deleted=false;
        TicketEntity ticket=ticketRepository.findById(id).get();

        //Get all user booked ticket list
        UserEntity user=ticket.getUser();
        List<TicketEntity> ticketEntities=user.getListOfTickets();

        //get all shows list for that ticket
        ShowEntity show=ticket.getShow();
        List<TicketEntity> ticketEntities1=show.getListOfBookedTickets();

        //get all booked seats
        List<ShowSeatsEntity> showSeatsEntities=show.getShowSeatsEntityList();

        for(ShowSeatsEntity showSeats:showSeatsEntities){
            TicketEntity ticket1=showSeats.getTicket();
            if(ticket1!=null && ticket1.getId()==id){
                showSeats.setBooked(false);
                showSeats.setBookAt(null);
                showSeats.setTicket(null);
                totalAmount+=ticket1.getAmount();
                ticketEntities.remove(ticket1);
                ticketEntities1.remove(ticket1);
                deleted=true;
            }
        }

        ticketRepository.deleteById(id);
        if(deleted){
            double refund= ((cancle_fee/100)*totalAmount);
            ticketResponseDto1.setMessage("Ticket Deleted Successfully");
            ticketResponseDto1.setRefunded_amount(refund);
        }else {
            ticketResponseDto1.setMessage("No tickets are booked");
        }
        return ticketResponseDto1;
    }


}

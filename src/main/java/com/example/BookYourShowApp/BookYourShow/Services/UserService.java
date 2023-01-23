package com.example.BookYourShowApp.BookYourShow.Services;


import com.example.BookYourShowApp.BookYourShow.Convertors.UserConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TicketEntity;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.UserRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){
        try{
            UserEntity user= UserConvertor.convertUserRequestToEntity(userRequestDto);
            userRepository.save(user);
        }catch (Exception e){
            log.info("Error while adding an user");
            return "user couldn't added";
        }
        return "User added successfully";
    }

    public UserResponseDto getUserByName(String name){
        try{
            UserEntity user=userRepository.findByName(name);
            UserResponseDto userResponseDto=UserConvertor.convertUserEntityToResponseDto(user);
            return userResponseDto;
        }catch (Exception e) {
            log.info("User not found");
            return null;
        }
    }

    public List<UserResponseDto> getAllUsers(){
        try {
            List<UserEntity> userEntities = userRepository.findAll();
            List<UserResponseDto> userResponseDtos=UserConvertor.convertListOfUserEntityToResponseDto(userEntities);
            return userResponseDtos;
        }catch (Exception e){
            return null;
        }
    }


    public List<UserResponseDto1> getAllTicketsByUser(int id){

        List<UserResponseDto1> userResponseDto1List=new ArrayList<>();
        UserEntity user=userRepository.findById(id).get();

        List<TicketEntity> bookedTickets=user.getListOfTickets();

        for(TicketEntity ticket:bookedTickets){
            UserResponseDto1 userResponseDto1=new UserResponseDto1();
            List<String> seatTypeList=new ArrayList<>();
            List<String> seatNoList=new ArrayList<>();

            ShowEntity show=ticket.getShow();

            for(ShowSeatsEntity showSeats:show.getShowSeatsEntityList()){
                seatTypeList.add(String.valueOf(showSeats.getSeatType()));
                seatNoList.add(showSeats.getSeatNo());
            }

            userResponseDto1.setSeatNo(seatNoList);
            userResponseDto1.setSeatType(seatTypeList);
            userResponseDto1.setUserName(user.getName());
            userResponseDto1.setAllotedSeats(ticket.getAlloted_seats());
            userResponseDto1.setMovieName(show.getMovie().getMovieName());
            userResponseDto1.setShowDate(show.getShowDate());
            userResponseDto1.setShowTime(show.getShowTime());
            userResponseDto1.setTheaterName(show.getTheater().getName());

            userResponseDto1List.add(userResponseDto1);
        }

        return userResponseDto1List;

    }
}

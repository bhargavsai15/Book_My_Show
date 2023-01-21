package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Convertors.TheaterConvertor;
import com.example.BookYourShowApp.BookYourShow.Enums.SeatType;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterSeatEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.TheaterRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.TheaterSeatRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.TheaterRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater= TheaterConvertor.convertTheaterRequestToEntity(theaterRequestDto);
        List<TheaterSeatEntity> theaterSeats=createTheaterEntity();

        theater.setTheaterSeatEntityList(theaterSeats);


        //for each theaterSeatEntity -> we need to set theaterEntity
        for(TheaterSeatEntity seats:theaterSeats){
            seats.setTheater(theater);
        }

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    public List<TheaterSeatEntity> createTheaterEntity(){

        List<TheaterSeatEntity> theaterSeatEntityList=new ArrayList<>();

//        TheaterSeatEntity theaterSeat1=new TheaterSeatEntity("2", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat2=new TheaterSeatEntity("3", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat3=new TheaterSeatEntity("4", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat4=new TheaterSeatEntity("5", SeatType.CLASSIC,100);
//        TheaterSeatEntity theaterSeat5=new TheaterSeatEntity("6", SeatType.PlATINUM,200);
//        TheaterSeatEntity theaterSeat6=new TheaterSeatEntity("7", SeatType.PlATINUM,200);
//        TheaterSeatEntity theaterSeat7=new TheaterSeatEntity("8", SeatType.PlATINUM,200);
//        TheaterSeatEntity theaterSeat8=new TheaterSeatEntity("9", SeatType.PlATINUM,200);
//
//        theaterSeatEntityList.add(theaterSeat1);
//        theaterSeatEntityList.add(theaterSeat2);
//        theaterSeatEntityList.add(theaterSeat3);
//        theaterSeatEntityList.add(theaterSeat4);
//        theaterSeatEntityList.add(theaterSeat5);
//        theaterSeatEntityList.add(theaterSeat6);
//        theaterSeatEntityList.add(theaterSeat7);
//        theaterSeatEntityList.add(theaterSeat8);

//        OPTIMIZED WAY

        boolean flag=true;
        for(int i=0;i<5;i++){
            char c= (char) ('A'+i);
            if(flag==true && i<=4){
                if(i==4){
                    flag=false;
                    i=-1;
                }
                String seatNo="1"+c;
                TheaterSeatEntity theaterSeat=new TheaterSeatEntity(seatNo, SeatType.CLASSIC,100);
                theaterSeatEntityList.add(theaterSeat);
            }else{
                String seatNo="2"+c;
                TheaterSeatEntity theaterSeat=new TheaterSeatEntity(seatNo, SeatType.PlATINUM,200);
                theaterSeatEntityList.add(theaterSeat);
            }
        }

        theaterSeatRepository.saveAll(theaterSeatEntityList);

        return theaterSeatEntityList;
    }


    public TheaterResponseDto getTheaterByName(String name){
        TheaterEntity theater=theaterRepository.findTheaterEntityByName(name);
        TheaterResponseDto theaterResponseDto=TheaterConvertor.convertDtoToEntity(theater);

        try{
            return theaterResponseDto;
        }catch (Exception e){
            return null;
        }
    }


}

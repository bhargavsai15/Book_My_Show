package com.example.BookYourShowApp.BookYourShow.Services;


import com.example.BookYourShowApp.BookYourShow.Convertors.ShowConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.*;
import com.example.BookYourShowApp.BookYourShow.Repositories.MovieRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.TheaterRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowSeatsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    public String addShow(ShowRequestDto showRequestDto){
        ShowEntity showEntity= ShowConvertor.convertShowRequestDtoToEntity(showRequestDto);

        //You need to get the movie repository
        MovieEntity movieEntity=movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theater repository
        TheaterEntity theaterEntity=theaterRepository.findById(showRequestDto.getTheaterId()).get();

        //set movie and theater for that show
        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);
        movieEntity.getShowAll().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);
        List<ShowSeatsEntity> showSeatEntity=createShowSeatEntity(theaterEntity.getTheaterSeatEntityList());
        showEntity.setShowSeatsEntityList(showSeatEntity);

        //for each show seat entity we can set to the show
        for(ShowSeatsEntity showSeatsEntity:showSeatEntity){
            showSeatsEntity.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "show Added Successfully";
    }

    public List<ShowSeatsEntity> createShowSeatEntity(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatsEntity> showSeats=new ArrayList<>();

        for(TheaterSeatEntity theaterSeat:theaterSeatEntityList){
            ShowSeatsEntity showSeatsEntity=ShowSeatsEntity.builder().
                                                seatNo(theaterSeat.getSeatNo()).
                                                seatType(theaterSeat.getSeatType()).build();
            showSeats.add(showSeatsEntity);
        }

        showSeatsRepository.saveAll(showSeats);
        return showSeats;
    }
}
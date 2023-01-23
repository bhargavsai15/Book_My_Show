package com.example.BookYourShowApp.BookYourShow.Services;


import com.example.BookYourShowApp.BookYourShow.Convertors.ShowConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.*;
import com.example.BookYourShowApp.BookYourShow.Repositories.MovieRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.Repositories.TheaterRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowSeatsRepository;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.ShowResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        MovieEntity movieEntity=movieRepository.findById(showRequestDto.getMovieId()).get();

        //You need to get the theater repository
        TheaterEntity theaterEntity=theaterRepository.findById(showRequestDto.getTheaterId()).get();

        //set movie and theater for that show
        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);

        //set the foreign key of movieEntity and TheaterEntity
        movieEntity.getShowAll().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        //creating showSeatEntities explicitly
        List<ShowSeatsEntity> showSeatEntity=createShowSeatEntity(theaterEntity.getTheaterSeatEntityList());
        showEntity.setShowSeatsEntityList(showSeatEntity);

        //for each show seat entity we can set to the show
        for(ShowSeatsEntity showSeatsEntity:showSeatEntity){
            showSeatsEntity.setShow(showEntity);
        }

        //It will not create duplicate entries for child coz id is same
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

    public List<ShowResponseDto> getAllShowsByGivenTime(String time1, String time2){
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2=LocalTime.parse(time2);

        List<ShowEntity> showEntities=showRepository.findByShowTimeBetween(t1,t2);
        List<ShowResponseDto> showResponseDtoList=getShows(showEntities);

        return showResponseDtoList;
    }

    public List<ShowResponseDto> getAllShowsByGivenDateAndTime(String date1, String time1, String date2, String time2) {
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2=LocalTime.parse(time2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2,formatter);

        List<ShowEntity> showEntities=showRepository.findByShowTimeBetweenAndShowDateBetween(t1,t2,localDate1,localDate2);
        List<ShowResponseDto> showResponseDtoList=getShows(showEntities);

        return showResponseDtoList;
    }

    public List<ShowResponseDto> getShows(List<ShowEntity> showEntities){
        List<ShowResponseDto> showResponseDtoList=new ArrayList<>();
        for(ShowEntity show:showEntities){
            ShowResponseDto showResponseDto=ShowResponseDto.builder().
                    id(show.getId()).
                    showDate(show.getShowDate()).
                    showTime(show.getShowTime()).
                    movieId(show.getMovie().getId()).
                    movieName(show.getMovie().getMovieName()).
                    theaterId(show.getTheater().getId()).
                    theaterName(show.getTheater().getName()).build();

            showResponseDtoList.add(showResponseDto);

        }
        return showResponseDtoList;
    }
}

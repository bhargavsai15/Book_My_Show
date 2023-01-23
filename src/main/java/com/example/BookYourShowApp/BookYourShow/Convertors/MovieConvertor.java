package com.example.BookYourShowApp.BookYourShow.Convertors;

import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Models.TheaterEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.ShowResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto1;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieConvertor {



    public static MovieEntity convertMovieRequestDtoToEntity(MovieRequestDto movieRequestDto){
        MovieEntity movie=MovieEntity.builder().
                                movieName(movieRequestDto.getMovieName()).
                                releaseDate(movieRequestDto.getReleaseDate()).
                                duration(movieRequestDto.getDuration()).build();
        return movie;
    }

    public static List<MovieResponseDto> convertListOfMovieEntityToResponseDto(List<MovieEntity> movie){
        List<MovieResponseDto> movieResponseDtos=new ArrayList<>();

        for(MovieEntity movies:movie){

            //converting movie entity to movie response dto
            MovieResponseDto movieResponseDto=MovieResponseDto.builder().
                    id(movies.getId()).
                    movieName(movies.getMovieName()).
                    duration(movies.getDuration()).
                    releaseDate(movies.getReleaseDate()).build();


            List<ShowResponseDto> showEntityList=new ArrayList<>();
            for(ShowEntity showEntities:movies.getShowAll()){

                ShowResponseDto showResponseDto=ShowResponseDto.builder().
                        id(showEntities.getId()).
                        showTime(showEntities.getShowTime()).
                        showDate(showEntities.getShowDate()).build();
                showEntityList.add(showResponseDto);
            }
            movieResponseDto.setShowAll(showEntityList);
            movieResponseDtos.add(movieResponseDto);
        }

        return movieResponseDtos;
    }

    public static List<TheaterResponseDto1> convertListOfMovieEntityToTheaterResponseDto(List<MovieEntity> movies){
        List<TheaterResponseDto1> theaterResponseDto1s=new ArrayList<>();
        for(MovieEntity movie:movies){


            //Get All Shows for that movie
            List<ShowEntity> shows=movie.getShowAll();

            //From movie entity we are accessing the theater name
            for (ShowEntity show:shows){

                TheaterEntity theater=show.getTheater();

                //Converting into Theater Response DTO
                TheaterResponseDto1 theaterResponseDto1= TheaterResponseDto1.builder().
                                                            id(theater.getId()).
                                                            name(theater.getName()).
                                                            city(theater.getCity()).
                                                            address(theater.getAddress()).build();
                theaterResponseDto1s.add(theaterResponseDto1);
            }

        }
        return theaterResponseDto1s;
    }
}

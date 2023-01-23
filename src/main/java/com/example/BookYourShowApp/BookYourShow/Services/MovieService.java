package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Convertors.MovieConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.MovieRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        try{
            MovieEntity movie= MovieConvertor.convertMovieRequestDtoToEntity(movieRequestDto);
            movieRepository.save(movie);
        }catch (Exception e){
            log.info("movie not added");
            return "Movie couldn't added";
        }

        return "Movie added successfully ";
    }

    public List<MovieResponseDto> getMovieByName(String name){
        List<MovieEntity> movie=movieRepository.findAllByMovieName(name);
        List<MovieResponseDto> movieResponseDto= MovieConvertor.convertListOfMovieEntityToResponseDto(movie);
        try{
            return movieResponseDto;
        }catch (Exception e){
            log.info("Movie not found Error");
            return null;
        }
    }

    public List<MovieResponseDto> getAllMovies(){
        List<MovieEntity> movieEntityList=movieRepository.findAll();
        List<MovieResponseDto> movieResponseDtoList=MovieConvertor.convertListOfMovieEntityToResponseDto(movieEntityList);
        return movieResponseDtoList;
    }

    public List<TheaterResponseDto1> getAllTheatersByMovieName(String name){

        //Get list of movie entities for that particular movie name
        List<MovieEntity> movieEntityList=movieRepository.findAllByMovieName(name);

        //Getting all the theaters for that movie
        List<TheaterResponseDto1> theaterResponseDto1s=MovieConvertor.convertListOfMovieEntityToTheaterResponseDto(movieEntityList);

        return theaterResponseDto1s;
    }


}

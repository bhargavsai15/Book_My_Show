package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Convertors.MovieConvertor;
import com.example.BookYourShowApp.BookYourShow.Convertors.ShowConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.MovieRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<MovieResponseDto> movieResponseDto= MovieConvertor.convertDtoToEntity(movie);
        try{
            return movieResponseDto;
        }catch (Exception e){
            log.info("Movie not found Error");
            return null;
        }
    }

    public List<MovieResponseDto> getAllMovies(){
        List<MovieEntity> movieEntityList=movieRepository.findAll();
        List<MovieResponseDto> movieResponseDtoList=MovieConvertor.convertMovieEntityToResponseDto(movieEntityList);
        return movieResponseDtoList;
    }

    public List<TheaterResponseDto> getAllTheatersByMovieName(String name){
        List<MovieEntity> movieEntityList=movieRepository.findAllByMovieName(name);
        List<TheaterResponseDto> theaterResponseDtos=new ArrayList<>();

        for(MovieEntity movie:movieEntityList){
            System.out.println(movie.getMovieName());
        }

        return theaterResponseDtos;
    }


}

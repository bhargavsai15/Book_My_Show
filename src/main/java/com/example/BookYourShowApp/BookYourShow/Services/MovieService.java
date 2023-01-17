package com.example.BookYourShowApp.BookYourShow.Services;

import com.example.BookYourShowApp.BookYourShow.Convertors.MovieConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.MovieRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

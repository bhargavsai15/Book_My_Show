package com.example.BookYourShowApp.BookYourShow.Convertors;

import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import lombok.Data;

@Data
public class MovieConvertor {

    public static MovieEntity convertMovieRequestDtoToEntity(MovieRequestDto movieRequestDto){
        MovieEntity movie=MovieEntity.builder().
                                movieName(movieRequestDto.getMovieName()).
                                releaseDate(movieRequestDto.getReleaseDate()).
                                duration(movieRequestDto.getDuration()).build();
        return movie;
    }
}

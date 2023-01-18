package com.example.BookYourShowApp.BookYourShow.Convertors;

import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.ShowRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.ShowResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    public static MovieResponseDto convertDtoToEntity(MovieEntity movie){
        MovieResponseDto movieResponseDto=MovieResponseDto.builder().
                                            id(movie.getId()).
                                            movieName(movie.getMovieName()).
                                            duration(movie.getDuration()).
                                            releaseDate(movie.getReleaseDate()).build();

        List<ShowResponseDto> showEntityList=new ArrayList<>();
        for(ShowEntity showEntities:movie.getShowAll()){
            ShowResponseDto showResponseDto=ShowResponseDto.builder().
                                                id(showEntities.getId()).
                                                showTime(showEntities.getShowTime()).
                                                showDate(showEntities.getShowDate()).
                                                updatedOn(showEntities.getUpdatedOn()).
                                                createdOn(showEntities.getCreatedOn()).build();
            showEntityList.add(showResponseDto);
        }

        movieResponseDto.setShowAll(showEntityList);

        return movieResponseDto;
    }
}

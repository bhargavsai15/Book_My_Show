package com.example.BookYourShowApp.BookYourShow.Controllers;


import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto1;
import com.example.BookYourShowApp.BookYourShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<String> addMovie(@RequestBody() MovieRequestDto movieRequestDto){
        String response=movieService.addMovie(movieRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get movie by name
    @GetMapping("/{name}")
    public ResponseEntity<List<MovieResponseDto>> getMovieByName(@PathVariable("name")String name){
        List<MovieResponseDto> movieResponseDtoList=movieService.getMovieByName(name);
        return new ResponseEntity<>(movieResponseDtoList,HttpStatus.FOUND);
    }

    @GetMapping("/all-movies")
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(){
        List<MovieResponseDto> movieResponseDtos=movieService.getAllMovies();
        return new ResponseEntity<>(movieResponseDtos,HttpStatus.FOUND);
    }

    @GetMapping("/theater/{name}")
    public ResponseEntity<List<TheaterResponseDto1>> getAllTheatersByMovieName(@PathVariable("name")String name){
        List<TheaterResponseDto1> theaterResponseDto1s=movieService.getAllTheatersByMovieName(name);
        return new ResponseEntity<>(theaterResponseDto1s,HttpStatus.FOUND);
    }
}

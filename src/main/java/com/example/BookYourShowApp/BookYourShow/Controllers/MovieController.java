package com.example.BookYourShowApp.BookYourShow.Controllers;


import com.example.BookYourShowApp.BookYourShow.Convertors.MovieConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.MovieRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.MovieResponseDto;
import com.example.BookYourShowApp.BookYourShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MovieResponseDto> getMovieByName(@PathVariable("name")String name){
        MovieResponseDto movie=movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<MovieEntity> getMovieById(@PathVariable("id")Integer id){
//
//    }
}

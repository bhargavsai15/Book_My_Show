package com.example.BookYourShowApp.BookYourShow.Controllers;

import com.example.BookYourShowApp.BookYourShow.RequestDtos.TheaterRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import com.example.BookYourShowApp.BookYourShow.Services.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theaters")
@Slf4j
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add-theater")
    public ResponseEntity<String> addTheater(@RequestBody()TheaterRequestDto theaterRequestDto){
        String response=theaterService.addTheater(theaterRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TheaterResponseDto> getTheaterByName(@PathVariable("name")String name){
        TheaterResponseDto theaterResponseDto=null;
        try{
            theaterResponseDto=theaterService.getTheaterByName(name);
            return new ResponseEntity<>(theaterResponseDto,HttpStatus.FOUND);
        }catch (Exception e){
            log.info("Theater not found");
            return null;
        }
    }

}

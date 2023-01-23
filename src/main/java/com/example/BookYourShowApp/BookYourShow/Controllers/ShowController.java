package com.example.BookYourShowApp.BookYourShow.Controllers;


import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.ShowResponseDto;
import com.example.BookYourShowApp.BookYourShow.Services.ShowService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add-show")
    public ResponseEntity<String> addShow(@RequestBody()ShowRequestDto showRequestDto){
        String response=showService.addShow(showRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-shows-by-time")
    public ResponseEntity<List<ShowResponseDto>>  getAllShowsByGivenTime(@RequestParam("from") String time1, @RequestParam("to")String time2){
        List<ShowResponseDto> showResponseDtos=showService.getAllShowsByGivenTime(time1,time2);
        return new ResponseEntity<>(showResponseDtos,HttpStatus.FOUND);
    }

    @GetMapping("/get-shows-by-time-and-date")
    public ResponseEntity<List<ShowResponseDto>> getAllShowsByGivenDateAndTime(@RequestParam("date1")String date1,@RequestParam("from") String time1,@RequestParam("date2")String date2,@RequestParam("to")String time2){
        List<ShowResponseDto> showResponseDtoList=showService.getAllShowsByGivenDateAndTime(date1,time1,date2,time2);
        return new ResponseEntity<>(showResponseDtoList,HttpStatus.FOUND);
    }

}

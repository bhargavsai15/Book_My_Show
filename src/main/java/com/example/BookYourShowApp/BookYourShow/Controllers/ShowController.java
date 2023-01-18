package com.example.BookYourShowApp.BookYourShow.Controllers;


import com.example.BookYourShowApp.BookYourShow.RequestDtos.ShowRequestDto;
import com.example.BookYourShowApp.BookYourShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

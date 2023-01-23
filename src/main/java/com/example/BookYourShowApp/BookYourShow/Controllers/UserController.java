package com.example.BookYourShowApp.BookYourShow.Controllers;

import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.TheaterResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto1;
import com.example.BookYourShowApp.BookYourShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    //Post user data
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody() UserRequestDto userRequestDto){
        String response=userService.createUser(userRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get user by name and also the list of booked tickets
    @GetMapping("/{name}")
    public ResponseEntity<UserResponseDto> getUserByName(@PathVariable("name")String name){
        UserResponseDto userResponseDto=userService.getUserByName(name);
        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);
    }



    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userEntityList=userService.getAllUsers();
        return new ResponseEntity<>(userEntityList,HttpStatus.FOUND);
    }

    @GetMapping("/all-tickets-by-user")
    public ResponseEntity<List<UserResponseDto1>> getAllTicketsByUser(@RequestParam("id")int id){
        List<UserResponseDto1> userResponseDto1List=userService.getAllTicketsByUser(id);
        return new ResponseEntity<>(userResponseDto1List,HttpStatus.FOUND);
    }
}

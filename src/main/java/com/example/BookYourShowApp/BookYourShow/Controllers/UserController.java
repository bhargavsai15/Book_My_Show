package com.example.BookYourShowApp.BookYourShow.Controllers;

import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import com.example.BookYourShowApp.BookYourShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody() UserRequestDto userRequestDto){
        String response=userService.createUser(userRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable("name")String name){
        UserEntity user=userService.getUserByName(name);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
}
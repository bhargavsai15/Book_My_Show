package com.example.BookYourShowApp.BookYourShow.Services;


import com.example.BookYourShowApp.BookYourShow.Convertors.UserConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.UserRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import com.example.BookYourShowApp.BookYourShow.ResponseDtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){
        try{
            UserEntity user= UserConvertor.convertUserRequestToEntity(userRequestDto);
            userRepository.save(user);
        }catch (Exception e){
            log.info("Error while adding an user");
            return "user couldn't added";
        }
        return "User added successfully";
    }

    public UserResponseDto getUserByName(String name){
        try{
            UserEntity user=userRepository.findByName(name);
            UserResponseDto userResponseDto=UserConvertor.convertUserEntityToResponseDto(user);
            return userResponseDto;
        }catch (Exception e) {
            log.info("User not found");
            return null;
        }
    }

    public List<UserResponseDto> getAllUsers(){
        try {
            List<UserEntity> userEntities = userRepository.findAll();
            List<UserResponseDto> userResponseDtos=UserConvertor.convertListOfUserEntityToResponseDto(userEntities);
            return userResponseDtos;
        }catch (Exception e){
            return null;
        }
    }
}

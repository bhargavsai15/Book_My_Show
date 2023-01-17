package com.example.BookYourShowApp.BookYourShow.Services;


import com.example.BookYourShowApp.BookYourShow.Convertors.UserConvertor;
import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import com.example.BookYourShowApp.BookYourShow.Repositories.UserRepository;
import com.example.BookYourShowApp.BookYourShow.RequestDtos.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserEntity getUserByName(String name){
        UserEntity user=null;
        try{
            user=userRepository.findByName(name);
        }catch (Exception e){
            log.info("User not found");
            return null;
        }
        return user;
    }
}

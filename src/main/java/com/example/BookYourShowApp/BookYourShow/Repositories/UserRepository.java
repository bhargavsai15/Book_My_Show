package com.example.BookYourShowApp.BookYourShow.Repositories;

import com.example.BookYourShowApp.BookYourShow.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByName(String name);
}

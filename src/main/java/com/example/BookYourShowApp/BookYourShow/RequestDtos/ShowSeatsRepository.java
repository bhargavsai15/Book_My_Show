package com.example.BookYourShowApp.BookYourShow.RequestDtos;

import com.example.BookYourShowApp.BookYourShow.Models.ShowSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeatsEntity,Integer> {
}

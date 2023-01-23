package com.example.BookYourShowApp.BookYourShow.Repositories;

import com.example.BookYourShowApp.BookYourShow.Models.ShowEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
    List<ShowEntity> findByShowTimeBetween(LocalTime time1,LocalTime time2);

    List<ShowEntity> findByShowTimeBetweenAndShowDateBetween(LocalTime time1, LocalTime time2, LocalDate date1,LocalDate date2);
}

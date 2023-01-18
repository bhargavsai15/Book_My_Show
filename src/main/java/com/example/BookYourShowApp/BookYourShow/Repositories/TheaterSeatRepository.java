package com.example.BookYourShowApp.BookYourShow.Repositories;

import com.example.BookYourShowApp.BookYourShow.Models.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity,Integer> {
}

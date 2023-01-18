package com.example.BookYourShowApp.BookYourShow.Repositories;

import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    MovieEntity findByMovieName(String name);
}

package com.example.BookYourShowApp.BookYourShow.Repositories;

import com.example.BookYourShowApp.BookYourShow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    List<MovieEntity> findAllByMovieName(String name);

//    MovieEntity findByMovieName(String name);
    //List<MovieEntity> findMovieEntitiesByMovieName(String name);
}

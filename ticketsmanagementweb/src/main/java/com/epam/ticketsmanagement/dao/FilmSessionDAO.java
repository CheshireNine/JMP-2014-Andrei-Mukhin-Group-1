package com.epam.ticketsmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.ticketsmanagement.model.FilmSession;

public interface FilmSessionDAO extends JpaRepository<FilmSession, Long> {
    @Query("select s from FilmSession s where s.film.id = :filmId")
    List<FilmSession> listBySessionId(@Param("filmId") Long filmId);
}

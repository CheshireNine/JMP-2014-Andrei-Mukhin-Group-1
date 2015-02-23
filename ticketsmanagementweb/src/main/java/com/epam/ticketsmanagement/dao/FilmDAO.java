package com.epam.ticketsmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.ticketsmanagement.model.Film;

public interface FilmDAO extends JpaRepository<Film, Long> {
}

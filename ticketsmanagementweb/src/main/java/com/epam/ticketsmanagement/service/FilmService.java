package com.epam.ticketsmanagement.service;

import java.util.List;

import com.epam.ticketsmanagement.model.Film;

public interface FilmService {
    List<Film> list();
    Film get(Long filmId);
    Film store(Film film);
    void delete(Film film);
}

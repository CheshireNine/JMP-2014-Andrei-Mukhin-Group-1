package com.epam.ticketsmanagement.conversion;

import com.epam.ticketsmanagement.model.Film;
import com.epam.ticketsmanagement.vo.FilmVO;

public class FilmsConversion {

    private FilmsConversion() {
    }

    public static Film toFilm(FilmVO filmVO) {
        Film film = new Film();
        film.setId(filmVO.getId());
        film.setName(filmVO.getName());
        film.setDescription(filmVO.getDescription());
        return film;
    }

    public static FilmVO fromFilm(Film film) {
        FilmVO filmVO = new FilmVO();
        filmVO.setId(film.getId());
        filmVO.setName(film.getName());
        filmVO.setDescription(film.getDescription());
        return filmVO;
    }
}

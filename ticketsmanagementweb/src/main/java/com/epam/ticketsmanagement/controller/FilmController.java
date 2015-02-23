package com.epam.ticketsmanagement.controller;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.ticketsmanagement.model.Film;
import com.epam.ticketsmanagement.model.FilmSession;
import com.epam.ticketsmanagement.service.FilmService;
import com.epam.ticketsmanagement.service.FilmSessionService;
import com.epam.ticketsmanagement.service.MappingService;
import com.epam.ticketsmanagement.service.impl.Action;
import com.epam.ticketsmanagement.vo.FilmVO;

@Transactional
@RestController
@RequestMapping(value = "/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmSessionService sessionService;
 
    @Autowired
    private MappingService mappingService;
 
    @RequestMapping(method = RequestMethod.GET)
    public List<Film> list() {
        List<Film> films = filmService.list();
        return films;
    }
    
    @RequestMapping(value = "/{filmId}", method = RequestMethod.GET)
    public FilmVO read(@PathVariable Long filmId) {
        Film film = filmService.get(filmId);
        List<FilmSession> sessions = sessionService.listByFilmId(filmId);
        FilmVO result = (FilmVO) mappingService.map(Action.FROM_FILM, film);
        result.setSessions(sessions);
        return result;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Film create(@RequestBody Film film) {
        Film mappedFilm = (Film) mappingService.map(Action.TO_FILM, film);
        Film persisted = filmService.store(mappedFilm);
        return persisted;
    }
 
    @RequestMapping(value = "/{filmId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long filmId) {
        Film film = filmService.get(filmId);
        filmService.delete(film);
    }

}

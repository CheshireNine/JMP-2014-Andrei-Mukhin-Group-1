package com.epam.ticketsmanagement.service;

import java.util.List;
import com.epam.ticketsmanagement.model.FilmSession;

public interface FilmSessionService {
    List<FilmSession> list();
    List<FilmSession> listByFilmId(Long filmId);
    FilmSession get(Long sessionId);
    FilmSession store(FilmSession session);
    void delete(FilmSession session);
}

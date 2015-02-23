package com.epam.ticketsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ticketsmanagement.dao.FilmSessionDAO;
import com.epam.ticketsmanagement.model.FilmSession;
import com.epam.ticketsmanagement.service.FilmSessionService;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    @Autowired
    private FilmSessionDAO sessionDAO;

    public FilmSessionServiceImpl() {
    }

    @Override
    public List<FilmSession> list() {
        return sessionDAO.findAll();
    }

    @Override
    public FilmSession get(Long sessionId) {
        return sessionDAO.findOne(sessionId);
    }

    @Override
    public FilmSession store(FilmSession session) {
        return sessionDAO.save(session);
    }

    @Override
    public void delete(FilmSession session) {
        sessionDAO.delete(session.getId());
    }

    @Override
    public List<FilmSession> listByFilmId(Long filmId) {
        return sessionDAO.listBySessionId(filmId);
    }

}

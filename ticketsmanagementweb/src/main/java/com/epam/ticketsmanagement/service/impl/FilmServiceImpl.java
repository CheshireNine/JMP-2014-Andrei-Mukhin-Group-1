package com.epam.ticketsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ticketsmanagement.dao.FilmDAO;
import com.epam.ticketsmanagement.model.Film;
import com.epam.ticketsmanagement.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmDAO filmDAO;

    public FilmServiceImpl() {
    }

    @Override
    public List<Film> list() {
        return filmDAO.findAll();
    }

    @Override
    public Film get(Long ticketId) {
        return filmDAO.findOne(ticketId);
    }

    @Override
    public Film store(Film ticket) {
        return filmDAO.save(ticket);
    }

    @Override
    public void delete(Film ticket) {
        filmDAO.delete(ticket.getId());
    }

}

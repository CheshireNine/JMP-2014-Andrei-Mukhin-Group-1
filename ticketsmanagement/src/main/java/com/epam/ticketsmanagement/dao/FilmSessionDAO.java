package com.epam.ticketsmanagement.dao;

import java.sql.Connection;

import com.epam.ticketsmanagement.model.FilmSession;

public interface FilmSessionDAO {
    FilmSession findById(Connection connection, long id);
    void update(Connection connection, FilmSession seffion);
}

package com.epam.ticketsmanagement.dao;

import java.sql.Connection;

import com.epam.ticketsmanagement.model.Ticket;

public interface TicketDAO {
    void insert(Connection connection, Ticket ticket);
}

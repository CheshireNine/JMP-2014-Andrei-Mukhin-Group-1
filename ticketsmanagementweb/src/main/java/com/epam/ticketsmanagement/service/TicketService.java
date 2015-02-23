package com.epam.ticketsmanagement.service;

import java.util.List;

import com.epam.ticketsmanagement.model.Ticket;

public interface TicketService {
    List<Ticket> list();
    Ticket get(Long ticketId);
    Ticket store(Ticket ticket);
    Ticket store(Ticket ticket, Long sessionId);
    void delete(Ticket ticket);
}

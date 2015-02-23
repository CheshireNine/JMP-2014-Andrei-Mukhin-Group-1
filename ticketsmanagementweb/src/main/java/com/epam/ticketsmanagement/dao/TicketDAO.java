package com.epam.ticketsmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.ticketsmanagement.model.Ticket;

public interface TicketDAO extends JpaRepository<Ticket, Long> {
}

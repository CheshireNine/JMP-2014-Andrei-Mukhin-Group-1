package com.epam.ticketsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.ticketsmanagement.dao.FilmSessionDAO;
import com.epam.ticketsmanagement.dao.HallDAO;
import com.epam.ticketsmanagement.dao.TicketDAO;
import com.epam.ticketsmanagement.model.FilmSession;
import com.epam.ticketsmanagement.model.Hall;
import com.epam.ticketsmanagement.model.Ticket;
import com.epam.ticketsmanagement.model.TicketType;
import com.epam.ticketsmanagement.service.TicketService;

@Service

public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketsDAO;

    @Autowired
    private FilmSessionDAO sessionDAO;

    @Autowired
    private HallDAO hallDAO;

    public TicketServiceImpl() {
    }

    @Override
    public List<Ticket> list() {
        return ticketsDAO.findAll();
    }

    @Override
    public Ticket get(Long ticketId) {
        return ticketsDAO.findOne(ticketId);
    }

    @Override
    public Ticket store(Ticket ticket) {
        return ticketsDAO.save(ticket);
    }

    @Override
    @Transactional
    public Ticket store(Ticket ticket, Long sessionId) {
        FilmSession session = sessionDAO.findOne(sessionId);
        // do ticket type specific logic
        if(ticket.getType() == TicketType.BOOKED) {
            // check that maximum hall booked seats number is not reached
            Hall hall = hallDAO.findOne(session.getHall().getId());
            if(session.getBookedSeats() == hall.getBookingSeatsNumber()) {
                // maximum is reached (process error handling)
                return null;
            }
            // increment booked seats by 1
            session.setBookedSeats(session.getBookedSeats() + 1);
        } else {
         // increment reserved seats by 1
            session.setReservedSeats(session.getReservedSeats() + 1);
        }
        // store session (necessary for seats numbers)
        ticket.setSession(session);
        // create new ticket
        return ticketsDAO.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketsDAO.delete(ticket.getId());
    }

}

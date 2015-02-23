package com.epam.ticketsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ticketsmanagement.dao.HallDAO;
import com.epam.ticketsmanagement.model.Hall;
import com.epam.ticketsmanagement.service.HallService;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallDAO hallDAO;

    public HallServiceImpl() {
    }

    @Override
    public List<Hall> list() {
        return hallDAO.findAll();
    }

    @Override
    public Hall get(Long ticketId) {
        return hallDAO.findOne(ticketId);
    }

    @Override
    public Hall store(Hall ticket) {
        return hallDAO.save(ticket);
    }

    @Override
    public void delete(Hall ticket) {
        hallDAO.delete(ticket.getId());
    }

}

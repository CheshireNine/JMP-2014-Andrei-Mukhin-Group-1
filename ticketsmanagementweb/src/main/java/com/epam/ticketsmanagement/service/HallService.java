package com.epam.ticketsmanagement.service;

import java.util.List;

import com.epam.ticketsmanagement.model.Hall;

public interface HallService {
    List<Hall> list();
    Hall get(Long hallId);
    Hall store(Hall hall);
    void delete(Hall hall);
}

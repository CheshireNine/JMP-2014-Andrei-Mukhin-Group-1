package com.epam.ticketsmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.ticketsmanagement.model.Hall;

public interface HallDAO extends JpaRepository<Hall, Long> {
}

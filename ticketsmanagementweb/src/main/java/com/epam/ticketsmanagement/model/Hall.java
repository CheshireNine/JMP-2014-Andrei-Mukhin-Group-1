package com.epam.ticketsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @Column(name = "hall_id", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "seats_num", length = 11)
    private int seatsNumber;

    @Column(name = "booking_seats_num", length = 11)
    private int bookingSeatsNumber;

    public Hall() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public int getBookingSeatsNumber() {
        return bookingSeatsNumber;
    }

    public void setBookingSeatsNumber(int bookingSeatsNumber) {
        this.bookingSeatsNumber = bookingSeatsNumber;
    }
}

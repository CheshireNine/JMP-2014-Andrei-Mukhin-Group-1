package com.epam.ticketsmanagement.model;

public class Hall {

    private long id;
    private String name;
    private int seatsNumber;
    private int bookingSeatsNumber;

    public Hall() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

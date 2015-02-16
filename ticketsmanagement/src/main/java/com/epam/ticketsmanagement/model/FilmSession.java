package com.epam.ticketsmanagement.model;

import java.util.Calendar;

public class FilmSession {

    private long id;
    private Hall hall;
    private Film film;
    private Calendar time;
    private int reservedSeats;
    private int bookedSeats;

    public FilmSession() {
    }

    public FilmSession(long id, Hall hall, Film film, Calendar time, int reservedSeats, int bookedSeats) {
        super();
        this.id = id;
        this.hall = hall;
        this.film = film;
        this.time = time;
        this.reservedSeats = reservedSeats;
        this.bookedSeats = bookedSeats;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public int getBooedSeats() {
        return bookedSeats;
    }

    public void setBooedSeats(int booedSeats) {
        this.bookedSeats = booedSeats;
    }
}

package com.epam.ticketsmanagement.model;

public class Ticket {

    private long id;
    private FilmSession session;
    private TicketType type;
    private int place;

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FilmSession getSession() {
        return session;
    }

    public void setSession(FilmSession session) {
        this.session = session;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

}

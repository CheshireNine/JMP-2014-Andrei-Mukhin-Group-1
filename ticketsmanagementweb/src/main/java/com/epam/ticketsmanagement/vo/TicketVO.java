package com.epam.ticketsmanagement.vo;

import com.epam.ticketsmanagement.model.TicketType;

public class TicketVO {

    private Long id;
    private Long sessionId;
    private TicketType type;
    private int place;

    public TicketVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}

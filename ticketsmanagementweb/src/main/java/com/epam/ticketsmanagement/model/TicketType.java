package com.epam.ticketsmanagement.model;

public enum TicketType {
    RESERVED("reserved"),
    BOOKED("booked");
    
    private String type;
    
    private TicketType( String type ) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

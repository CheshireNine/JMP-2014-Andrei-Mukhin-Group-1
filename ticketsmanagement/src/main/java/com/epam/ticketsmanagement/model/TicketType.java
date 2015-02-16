package com.epam.ticketsmanagement.model;

public enum TicketType {
    RESERVED(1),
    BOOKED(2);
    
    private int type;
    
    private TicketType( int type )
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }
}

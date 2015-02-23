package com.epam.ticketsmanagement.conversion;

import com.epam.ticketsmanagement.model.Ticket;
import com.epam.ticketsmanagement.vo.TicketVO;

public class TicketsConversion {

    private TicketsConversion() {
    }

    public static Ticket toTicket(TicketVO ticketVO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketVO.getId());
        ticket.setPlace(ticketVO.getPlace());
        ticket.setType(ticketVO.getType());
        return ticket;
    }

    public static TicketVO fromTicket(Ticket ticket) {
        TicketVO ticketVO = new TicketVO();
        ticketVO.setId(ticket.getId());
        ticketVO.setPlace(ticket.getPlace());
        ticketVO.setType(ticket.getType());
        return ticketVO;
    }
}

package com.epam.ticketsmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.ticketsmanagement.model.Ticket;
import com.epam.ticketsmanagement.service.FilmSessionService;
import com.epam.ticketsmanagement.service.HallService;
import com.epam.ticketsmanagement.service.MappingService;
import com.epam.ticketsmanagement.service.TicketService;
import com.epam.ticketsmanagement.service.impl.Action;
import com.epam.ticketsmanagement.vo.TicketVO;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    private static Logger LOGGER = Logger.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FilmSessionService sessionService;

    @Autowired
    private HallService hallService;
 
    @Autowired
    private MappingService mappingService;
 
    
    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    public Ticket read(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.get(ticketId);
        return ticket;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object create(@RequestBody TicketVO ticket) {
        // convert VO to real model object
        Ticket mappedTicket = (Ticket) mappingService.map(Action.TO_TICKET, ticket);
        // create new ticket
        Ticket persisted = ticketService.store(mappedTicket, ticket.getSessionId());

        if(persisted == null) {
            // maximum is reached (process error handling)
            Map<String, String>  map = new HashMap<String, String>();
            map.put("error", "Request Error");
            map.put("cause", "Maximum of booked seats for this session is reached!");
            return map;
        }
        LOGGER.info("New ticket is created. Type: " + ticket.getType());
        return persisted;
    }
 
    @RequestMapping(value = "/{ticketId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long ticketId) {
        Ticket ticket = ticketService.get(ticketId);
        ticketService.delete(ticket);
    }

}

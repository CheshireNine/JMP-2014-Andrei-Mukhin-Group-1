package com.epam.ticketsmanagement.service.impl;

import com.epam.ticketsmanagement.conversion.FilmsConversion;
import com.epam.ticketsmanagement.conversion.TicketsConversion;
import com.epam.ticketsmanagement.model.Film;
import com.epam.ticketsmanagement.model.Ticket;
import com.epam.ticketsmanagement.vo.FilmVO;
import com.epam.ticketsmanagement.vo.TicketVO;

public enum Action {
    TO_TICKET {
        public Object doConversion(Object conversionObj) {
            return TicketsConversion.toTicket((TicketVO)conversionObj);
        }

    }, 
    FROM_TICKET {
        public Object doConversion(Object conversionObj) {
            return TicketsConversion.fromTicket((Ticket)conversionObj);
        }
    },
    TO_FILM {
        public Object doConversion(Object conversionObj) {
            return FilmsConversion.toFilm((FilmVO)conversionObj);
        }

    }, 
    FROM_FILM {
        public Object doConversion(Object conversionObj) {
            return FilmsConversion.fromFilm((Film)conversionObj);
        }
    };

    public abstract Object doConversion(Object conversionObj);
}

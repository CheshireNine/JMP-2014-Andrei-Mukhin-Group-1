package com.epam.ticketsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.epam.ticketsmanagement.dao.TicketDAO;
import com.epam.ticketsmanagement.model.Ticket;


public class JDBCTicketDAOImpl implements TicketDAO {

    private static final Logger LOG = Logger.getLogger(JDBCTicketDAOImpl.class);
    
    private static final String INSERT_TICKET_QUERY = "INSERT INTO ticket " +
            "(`session_id`, `place`, `type`) VALUES (?, ?, ?)";

    public JDBCTicketDAOImpl() {
    }

    public void insert(Connection connection, Ticket ticket){

      PreparedStatement ps = null;
      try {

          ps = connection.prepareStatement(INSERT_TICKET_QUERY);
          ps.setLong(1, ticket.getSession().getId());
          ps.setInt(2, ticket.getPlace());
          ps.setInt(3, ticket.getType().getType());

          ps.executeUpdate();
      } catch (SQLException e) {
          LOG.error(e.getMessage());
          throw new RuntimeException(e);
      }
  }

}

package com.epam.ticketsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.epam.ticketsmanagement.dao.FilmSessionDAO;

import com.epam.ticketsmanagement.model.Film;
import com.epam.ticketsmanagement.model.FilmSession;
import com.epam.ticketsmanagement.model.Hall;


public class JDBCFilmSessionDAOImpl implements FilmSessionDAO {

    private static final Logger LOG = Logger.getLogger(JDBCFilmSessionDAOImpl.class);

    private static final String FIND_SESSION_BY_ID_QUERY = "SELECT * FROM film_session WHERE session_id = ?";
    private static final String SESSION_UPDATE_QUERY = "UPDATE film_session " +
            "SET `hall_id` = ?, " +
            "`film_id` = ?, " +
            "`time` = ?, " +
            "`reserved_seats` = ?, " +
            "`booked_seats` = ? " +
            "WHERE session_id = ?";

    public JDBCFilmSessionDAOImpl() {
    }

    public void update(Connection connection, FilmSession session) {


      PreparedStatement ps = null;
      try {

          ps = connection.prepareStatement(SESSION_UPDATE_QUERY);
          ps.setLong(1, session.getHall().getId());
          ps.setLong(2, session.getFilm().getId());
          ps.setTimestamp(3, new Timestamp(session.getTime().getTimeInMillis()));
          ps.setInt(4, session.getReservedSeats());
          ps.setInt(5, session.getBooedSeats());
          ps.setLong(6, session.getId());

          ps.executeUpdate();
      } catch (SQLException e) {
          LOG.error(e.getMessage());
          throw new RuntimeException(e);
      }
  }
 
    public FilmSession findById(Connection connection, long id){

        FilmSession session = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_SESSION_BY_ID_QUERY);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Calendar time = Calendar.getInstance();
                time.setTimeInMillis(rs.getTimestamp("time").getTime());
                Hall hall = new Hall();
                hall.setId(rs.getLong("hall_id"));
                Film film = new Film();
                film.setId(rs.getLong("film_id"));
                session = new FilmSession(
                    rs.getLong("session_id"),
                    hall,
                    film,
                    time,
                    rs.getInt("reserved_seats"), 
                    rs.getInt("booked_seats")
                );
            }
            rs.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return session;
    }
}

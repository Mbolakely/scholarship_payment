package org.project.projet_bourse.Dao;

import org.project.projet_bourse.Model.CalendarModel;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CalendarDao {
    private static String url="jdbc:mysql://localhost:3306/bourse?useSSL=false";

    private static final String SELECT_SQL = "select * from session;";
    private static final String INSERT_SQL = "INSERT INTO session" + " (date_session, date_begin, date_last) VALUE " + " (?, ?, ?);";
    private static final String DELETE_SQL = "delete from session where session_id = ?;";
    private static final String GET_SESSION_SQL = "select session_id, date_session, date_begin, date_last from session where session_id=?;";
    private static final String UPDATE_SQL = "update session set date_session=?, date_begin=?, date_last=? where session_id=?;";

    private static Connection connection = null;

    protected static Connection getConnection() {
        System.out.println("Connexxxiiooon");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("connected to bd");
        }catch(Exception e) {
            System.out.println("NOT connected to bd");
        }
        return connection;
    }

    //INSERT CALENDAR FOR SESSION
    public static void insertCalendar(CalendarModel calendar) throws SQLException {
        System.out.println(INSERT_SQL);
        try (
            Connection connexion = getConnection();
            PreparedStatement stm = connexion.prepareStatement(INSERT_SQL)) {
            stm.setDate(1, calendar.getDate_session());
            stm.setDate(2, calendar.getDate_begin());
            stm.setDate(3, calendar.getDate_last());
            System.out.println(stm);
            stm.executeUpdate();
        } catch (SQLException e) {
            // error catching
        }
    }

    //LISTS OF SESSION
    public List<CalendarModel> selectAllCalendar() {
        List<CalendarModel> calendars = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(SELECT_SQL);
            System.out.println("Before sql query ----------");
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while(res.next()) {
                int session_id = res.getInt("session_id");
                Date date_session = res.getDate("date_session");
                Date date_begin = res.getDate("date_begin");
                Date date_last = res.getDate("date_last");
                calendars.add(new CalendarModel(session_id, date_session, date_begin, date_last));
                System.out.println("Displaying all the calendars");
                System.out.println(res);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return calendars;
    }

    // DELETE SESSION
    public boolean deleteCalendar(int session_id) throws SQLException {
        boolean deletedCalendar;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(DELETE_SQL)) {
            stm.setInt(1, session_id);
            deletedCalendar = stm.executeUpdate() > 0;
        }
        return deletedCalendar;
    }

    //GETTING CALENDAR BY ID
    public CalendarModel getCalendar(int session_id) {
        System.out.println("getting calendar funtion");
        CalendarModel calendar = null;
        try (Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(GET_SESSION_SQL);) {
            stm.setInt(1, session_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                Date date_session = res.getDate("date_session");
                Date date_begin = res.getDate("date_begin");
                Date date_last = res.getDate("date_last");
                calendar = new CalendarModel(session_id, date_session, date_begin, date_last);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return calendar;
    }

    //UPDATING CALENDAR RECORD
    public boolean updateCalendar(CalendarModel calendar) throws SQLException {
        boolean updatedCalendar;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(UPDATE_SQL);) {
            System.out.println("Updating calendar :" + stm);
            stm.setDate(1, calendar.getDate_session());
            stm.setDate(2, calendar.getDate_begin());
            stm.setDate(3, calendar.getDate_last());
            stm.setInt(4, calendar.getSession_id());
            updatedCalendar = stm.executeUpdate() > 0;
            System.out.println(updatedCalendar);
        }
        return updatedCalendar;
    }
}

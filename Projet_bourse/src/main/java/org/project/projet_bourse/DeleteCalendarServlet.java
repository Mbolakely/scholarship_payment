package org.project.projet_bourse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.CalendarDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteCalendarServlet", value = "/deleteCalendar")

public class DeleteCalendarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String calendarIdParam = request.getParameter("session_id");
        try {
            int session_id = Integer.parseInt(calendarIdParam);
            CalendarDao calendarDao = new CalendarDao();
            boolean result = calendarDao.deleteCalendar(session_id);
            response.sendRedirect("calendar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

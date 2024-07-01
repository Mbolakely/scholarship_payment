package org.project.projet_bourse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.CalendarDao;
import org.project.projet_bourse.Model.CalendarModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "calendarServlet", value = "/calendar")

public class calendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CalendarDao calendarDao;

    public void init() throws ServletException {
        System.out.println("print in calendar show servlet");
        calendarDao = new CalendarDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("try part in doGet showing all calendars");
            List<CalendarModel> calendarList = calendarDao.selectAllCalendar();
            request.setAttribute("calendarList", calendarList);
            System.out.println(calendarList.get(0).getDate_session());
            RequestDispatcher dispatcher = request.getRequestDispatcher("calendar.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

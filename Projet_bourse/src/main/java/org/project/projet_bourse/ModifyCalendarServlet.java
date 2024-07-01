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
import java.text.SimpleDateFormat;

@WebServlet(name = "modifyCalendarServlet", value = "/modifyCalendar")
public class ModifyCalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CalendarDao calendarDao;

    public void init() throws ServletException {
        System.out.println("print in modify calendar servlet");
        calendarDao = new CalendarDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String session_idParam = request.getParameter("session_id");
        if(session_idParam != null && !session_idParam.trim().isEmpty()) {
            try {
                int session_id = Integer.parseInt(session_idParam);
                CalendarModel existingCalendar;
                try {
                    existingCalendar = calendarDao.getCalendar(session_id);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("calendar.jsp");
                    request.setAttribute("session_id", existingCalendar.getSession_id());
                    request.setAttribute("date_session", existingCalendar.getDate_session());
                    request.setAttribute("date_begin", existingCalendar.getDate_begin());
                    request.setAttribute("date_last", existingCalendar.getDate_last());

                    dispatcher.forward(request, response);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("else part foer getting calendar to update");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        try {
            int session_id = Integer.parseInt(request.getParameter("session_id"));
            System.out.println("try part in doPost modify calendar");

            String date_session_param = request.getParameter("date_session");
            if (date_session_param == null || date_session_param.trim().isEmpty()) {
                System.out.println("vide session date");
                return;
            }
            SimpleDateFormat date_session = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDsession = date_session.parse(date_session_param);
            java.sql.Date sqlDsession = new java.sql.Date(utilDsession.getTime());

            String date_begin_param = request.getParameter("date_begin");
            if (date_begin_param == null || date_begin_param.trim().isEmpty()) {
                System.out.println("vide session begin");
                return;
            }
            SimpleDateFormat date_begin = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDbegin = date_begin.parse(date_begin_param);
            java.sql.Date sqlDbegin = new java.sql.Date(utilDbegin.getTime());

            String date_last_param = request.getParameter("date_last");
            if (date_last_param == null || date_last_param.trim().isEmpty()) {
                System.out.println("vide date last");
                return;
            }
            SimpleDateFormat date_last = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDlast = date_last.parse(date_last_param);
            java.sql.Date sqlDlast = new java.sql.Date(utilDlast.getTime());

            CalendarModel newCalendar = new CalendarModel(session_id, sqlDsession, sqlDbegin, sqlDlast);
            calendarDao.updateCalendar(newCalendar);
            response.sendRedirect("calendar");
        } catch (Exception e) {
            System.out.println("Error while updating calendar record:"+ e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid money id format catch in doPost money edit");
        }
    }
}

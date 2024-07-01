<%@ page import="java.util.List" %>
<%@ page import="org.project.projet_bourse.Model.CalendarModel" %><%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 26/06/2024
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
    <link rel="stylesheet" href="static/calendar.scss">
</head>
<body>
    <div class="calendar">
        <div class="sidebar">
            <div class="sidebar-title">
                <span class="title-first">Madagascar</span>
                <span class="title-second">Scholarship</span>
                <span class="title-third">Payment</span>
            </div>
            <hr class="divider">
            <div class="sidebar-menu">
                <div class="sidebar-item">
                    <div class="sidebar-icon" id="student">i</div>
                    <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/students">Students</a></span>
                </div>
                <div class="sidebar-item">
                    <div class="sidebar-icon" id="calendar">i</div>
                    <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/money">Money</a></span>
                </div>
                <div class="sidebar-item active">
                    <div class="sidebar-icon active" id="payment">i</div>
                    <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/calendar">Calendar</a></span>
                </div>
                <div class="sidebar-item">
                    <div class="sidebar-icon" id="setting">i</div>
                    <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/payment">Payment</a></span>
                </div>
                <div class="sidebar-item">
                    <div class="sidebar-icon" id="search">i</div>
                    <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/lateComer">Emailing</a></span>
                </div>
            </div>
        </div>
        <div class="calendar-page">
            <div class="header">
                <div class="header-first"><span class="money-title">CALENDARS</span></div>
                <div class="header-rounded"></div>
            </div>
            <div class="calendar-body">
                <div class="calendar-left">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Pay date</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <% List<CalendarModel> calendars = (List<CalendarModel>) request.getAttribute("calendarList");
                            if (calendars != null && !calendars.isEmpty()) {
                                for (CalendarModel calendar : calendars)
                                {
                            %>
                            <tr>
                                <td><%= calendar.getSession_id()%></td>
                                <td><%= calendar.getDate_session()%></td>
                                <td><%= calendar.getDate_begin()%></td>
                                <td><%= calendar.getDate_last()%></td>
                                <td class="actions-td">
                                    <a>View</a>
                                    <a href="/Projet_bourse_war_exploded/modifyCalendar?session_id=<%= calendar.getSession_id()%>">Edit</a>
                                    <a href="/Projet_bourse_war_exploded/deleteCalendar?session_id=<%= calendar.getSession_id()%>">Delete</a>
                                </td>
                            </tr>
                            <% }
                            } else { %>
                            <tr>
                                <td colspan="8">No calendar record found.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="calendar-right">
                    <form class="form-calendar" action="addCalendar" method="post">
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_session">Pay date</label>
                            <input class="calendar-input" name="date_session" id="date_session" type="date">
                        </div>
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_begin">From</label>
                            <input class="calendar-input" name="date_begin" id="date_begin" type="date">
                        </div>
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_last">Pay date</label>
                            <input class="calendar-input" name="date_last" id="date_last" type="date">
                        </div>
                        <input class="calendar-submit" type="submit" value="Submit calendar">
                    </form>
                    <form class="form-calendar" action="modifyCalendar" method="post">
                        <input type="hidden" name="session_id" id="session_id" value="<%= request.getAttribute("session_id")%>">
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_session">Pay date</label>
                            <input class="calendar-input" name="date_session" id="date_session" type="date" value="<%= request.getAttribute("date_session")%>">
                        </div>
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_begin">From</label>
                            <input class="calendar-input" name="date_begin" id="date_begin" type="date" value="<%= request.getAttribute("date_begin")%>">
                        </div>
                        <div class="inputCalendar-item">
                            <label class="calendar-label" for="date_last">Pay date</label>
                            <input class="calendar-input" name="date_last" id="date_last" type="date" value="<%= request.getAttribute("date_last")%>">
                        </div>
                        <input class="calendar-submit" type="submit" value="Update calendar">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

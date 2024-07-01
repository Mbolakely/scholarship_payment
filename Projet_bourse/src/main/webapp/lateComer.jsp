<%@ page import="org.project.projet_bourse.Model.StudentModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 28/06/2024
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Late comer</title>
    <link rel="stylesheet" href="static/lateComer.scss">
</head>
<body>
<div class="lateComer">
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
            <div class="sidebar-item">
                <div class="sidebar-icon" id="payment">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/calendar">Calendar</a></span>
            </div>
            <div class="sidebar-item">
                <div class="sidebar-icon" id="setting">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/payment">Payment</a></span>
            </div>
            <div class="sidebar-item active">
                <div class="sidebar-icon active" id="search">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/lateComer">Emailing</a></span>
            </div>
        </div>
    </div>
    <div class="lateComer-page">
        <div class="header">
            <div class="header-first">
                <div class="header-round"></div>
                <div class="icon-part">
                    <h2 class="titre">NON PAYED STUDENTS</h2>
                    <div class="buttonWithIcon">
                        <div class="button-icon add"></div>
                        <span class="button-text">
                 <a href="/Projet_bourse_war_exploded/addPayment">Reformulate email</a>
             </span>
                    </div>
                </div>
            </div>
        </div>
        <table border="1">
            <thead>
            <tr>
                <th>Matricule</th>
                <th>Name</th>
                <th>Sexe</th>
                <th>Birthday</th>
                <th>Institution</th>
                <th>level</th>
                <th>Email</th>
                <th>School year</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% List<StudentModel> students = (List<StudentModel>) request.getAttribute("lateList");
                if (students!= null && !students.isEmpty()) {
                    for (StudentModel student : students) { %>
            <tr>
                <td><%= student.getMatricule() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getSexe() %></td>
                <td><%= student.getBirthday() != null ? student.getBirthday().toLocalDate() : "Non disponible" %></td>
                <td><%= student.getInstitution() %></td>
                <td><%= student.getLevel() %></td>
                <td><%= student.getEmail() %></td>
                <td><%= student.getYear() %></td>
                <td class="actions-td">
                    <form action="send-email" method="get">
                        <input type="hidden" name="student_id" value="<%= student.getStudent_id() %>">
                        <button type="submit">send Email</button>
                    </form>

                </td>
            </tr>
            <% }
            } else { %>
            <tr>
                <td colspan="8">No late student found.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

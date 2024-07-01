<%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 21/06/2024
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.project.projet_bourse.Model.StudentModel" %>

<html>
<head>
    <title>students</title>
    <link rel="stylesheet" href="static/students.scss">
</head>
<body>
<div class="students">
    <div class="sidebar">
        <div class="sidebar-title">
            <span class="title-first">Madagascar</span>
            <span class="title-second">Scholarship</span>
            <span class="title-third">Payment</span>
        </div>
        <hr class="divider">
        <div class="sidebar-menu">
            <div class="sidebar-item active">
                <div class="sidebar-icon active" id="student">i</div>
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
            <div class="sidebar-item">
                <div class="sidebar-icon" id="search">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/lateComer">Emailing</a></span>
            </div>
        </div>
    </div>
   <div class="students-page">
      <div class="second_header">
          <div class="heading-second">
              <div class="heading-round"></div>
              <div class="icon-part">
                  <h2 class="titre">STUDENTS LIST</h2>
                  <div class="buttonWithIcon">
                      <div class="button-icon add"></div>
                      <span class="button-text">
                 <a href="/Projet_bourse_war_exploded/addStudent">Add new student</a>
             </span>
                  </div>
              </div>
          </div>
      </div>
      <div class="students-contain">
          <div class="search-part">
              <form action="searchStudent" class="search-form">
                  <input  class="input-simple" type="text" name="searchParameter" id="searchParameter">
                  <input type="submit" value="Search" class="input-button">
              </form>
          </div>
        <div class="students-contain-first">
            <form action="filterStudents" method="get" class="form-filter">
                <input type="submit" value="Filter by" class="input-button">
                <label for="level">level:</label>
                   <input type="text" id="level" name="level"  class="input-simple">
                <label for="institution">institution:</label>
                    <input type="text" id="institution" name="institution" class="input-simple">
            </form>
               <div class="buttonNoBackground">
                   <div class="buttonNoBackground-icon refresh"></div>
                   <a href="/Projet_bourse_war_exploded/students">Refresh list</a>
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
              <% List<StudentModel> students = (List<StudentModel>) request.getAttribute("listStudent");
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
                      <a href="/Projet_bourse_war_exploded/updateStudent?student_id=<%= student.getStudent_id()%>">Edit</a>
                      <a href="/Projet_bourse_war_exploded/deleteStudent?student_id=<%= student.getStudent_id()%>">Delete</a>
                  </td>
              </tr>
              <% }
              } else { %>
              <tr>
                  <td colspan="8">No students found.</td>
              </tr>
              <% } %>
              </tbody>
          </table>
          <h2><a href="/Projet_bourse_war_exploded/minorStudent">Minor students</a></h2>
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
              <% List<StudentModel> minorStudents = (List<StudentModel>) request.getAttribute("minors");
                  if (minorStudents!= null && !minorStudents.isEmpty()) {
                      for (StudentModel student : minorStudents) { %>
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
                      <a>View</a>
                      <a href="/Projet_bourse_war_exploded/updateStudent?student_id=<%= student.getStudent_id()%>">Edit</a>
                      <a href="/Projet_bourse_war_exploded/deleteStudent?student_id=<%= student.getStudent_id()%>">Delete</a>
                  </td>
              </tr>
              <% }
              } else { %>
              <tr>
                  <td colspan="8">No minor students displayed</td>
              </tr>
              <% } %>
              </tbody>
          </table>
      </div>
   </div>
</div>
</body>
</html>

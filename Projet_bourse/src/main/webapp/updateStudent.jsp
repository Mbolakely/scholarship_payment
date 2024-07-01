<%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 22/06/2024
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update student</title>
    <link rel="stylesheet" href="static/addStudent.scss">
</head>
<body>
<div class="addStudent">
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
    <div class="addStudent-page">
        <div class="heading-profile">
            <div class="heading-first"><span class="add-text">UPDATE STUDENT</span></div>
            <div class="heading-second"></div>
        </div>
        <div class="addStudent-form-container">
            <form class="addStudent-form" action="updateStudent" method="post">
               <div class="form-contain">
                   <input type="hidden" name="student_id" value="<%= request.getAttribute("student_id") %>">
                   <div class="form-contain-left">
                      <div class="add-item">
                          <label class="add-label" for="matricule">Matricule:</label>
                          <input class="add-input" type="text" id="matricule" name="matricule" value="<%= request.getAttribute("matricule")%>">
                      </div>

                       <div class="add-item">
                           <label class="add-label" for="name">Nom:</label>
                           <input class="add-input" type="text" id="name" name="name" value="<%= request.getAttribute("name")%>">
                       </div>

                      <div class="add-item">
                          <label class="add-label" for="sexe">Sexe:</label>
                          <select class="add-input" name="sexe" id="sexe">
                              <option value="F" <%= request.getAttribute("sexe").equals("F")? "selected" : "" %>>F</option>
                              <option value="M" <%= request.getAttribute("sexe").equals("M")? "selected" : "" %>>M</option>
                          </select>
                      </div>

                      <div class="add-item">
                          <label class="add-label" for="birthday">Date de naissance:</label>
                          <input class="add-input" type="date" id="birthday" name="birthday" value="<%= request.getAttribute("birthday").toString() %>">
                      </div>
                   </div>
                  <div class="form-contain-right">
                      <div class="add-item">
                          <label class="add-label" for="institution">Institution:</label>
                          <input class="add-input" type="text" id="institution" name="institution" value="<%= request.getAttribute("institution")%>">
                      </div>

                     <div class="add-item">
                         <label class="add-label" for="level">Niveau d'études:</label>
                         <select class="add-input" id="level" name="level">
                             <option value="L1" <%= request.getAttribute("level").equals("L1")? "selected" : "" %>>L1</option>
                             <option value="L2" <%= request.getAttribute("level").equals("L2")? "selected" : "" %>>L2</option>
                             <option value="L3" <%= request.getAttribute("level").equals("L3")? "selected" : "" %>>L3</option>
                             <option value="M1" <%= request.getAttribute("level").equals("M1")? "selected" : "" %>>M1</option>
                             <option value="M2" <%= request.getAttribute("level").equals("M2")? "selected" : "" %>>M2</option>
                         </select>
                     </div>

                      <div class="add-item">
                          <label class="add-label" for="email">Email:</label>
                          <input class="add-input" type="email" id="email" name="email" value="<%= request.getAttribute("email")%>">
                      </div>

                      <div class="add-item">
                          <label class="add-label" for="year">Année:</label>
                          <input class="add-input" type="text" id="year" name="year" value="<%= request.getAttribute("year")%>">
                      </div>
                  </div>
               </div>
                <input class="add-submit" type="submit" value="Update student">
            </form>
        </div>
    </div>
</div>
</body>
</html>

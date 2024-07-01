<%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 22/06/2024
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="static/addStudent.scss">
    <title>New student</title>
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
            <div class="heading-first"><span class="add-text">ADD NEW STUDENT</span></div>
            <div class="heading-second"></div>
        </div>
        <div class="addStudent-form-container">
            <form class="addStudent-form" action="addStudent" method="post">
               <div class="form-contain">
                   <div class="form-contain-left">
                      <div class="add-item">
                          <label class="add-label" for="matricule">Matricule</label>
                          <input class="add-input" type="text" name="matricule" id="matricule">
                      </div>
                       <div class="add-item">
                           <label class="add-label" for="name">Name</label>
                           <input class="add-input" type="text" name="name" id="name">
                       </div>
                       <div class="add-item">
                           <label class="add-label" for="sexe">Sexe</label>
                           <select class="add-input" name="sexe" id="sexe">
                               <option value="F">F</option>
                               <option value="M">M</option>
                           </select>
                       </div>
                       <div class="add-item">
                           <label class="add-label" for="birthday">Birthday</label>
                           <input class="add-input" type="date" name="birthday" id="birthday">
                       </div>
                   </div>
                   <div class="form-contain-right">
                      <div class="add-item">
                          <label class="add-label" for="institution">institution</label>
                          <input class="add-input" type="text" name="institution" id="institution">
                      </div>
                      <div class="add-item">
                          <label class="add-label" for="email">email</label>
                          <input class="add-input" type="email" name="email" id="email">
                      </div>
                       <div class="add-item">
                           <label class="add-label" for="year">Year</label>
                           <input class="add-input" type="text" name="year" id="year">
                       </div>
                       <div class="add-item">
                           <label class="add-label" for="level">level</label>
                           <select class="add-input" name="level" id="level">
                               <option value="L1">L1</option>
                               <option value="L2">L2</option>
                               <option value="L3">L3</option>
                               <option value="M1">M1</option>
                               <option value="M2">M2</option>
                           </select>
                       </div>
                   </div>
               </div>
                <input class="add-submit" type="submit" value="Submit student">
            </form>
        </div>
    </div>
</div>
</body>
</html>

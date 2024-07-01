<%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 27/06/2024
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add payment</title>
    <link rel="stylesheet" href="static/addPayment.scss">
</head>
<body>
<div class="addpayment">
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
            <div class="sidebar-item active">
                <div class="sidebar-icon active" id="setting">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/payment">Payment</a></span>
            </div>
            <div class="sidebar-item">
                <div class="sidebar-icon" id="search">i</div>
                <span class="sidebar-text"><a href="/Projet_bourse_war_exploded/lateComer">Emailing</a></span>
            </div>
        </div>
    </div>
    <div class="addPayment-page">
        <div class="heading-profile">
            <div class="heading-first"><span class="add-text">ADD PAYMENT</span></div>
            <div class="heading-second"></div>
        </div>
        <div class="addPayment-form-container">
            <form class="addPayment-form" action="addPayment" method="post">
                <div class="form-contain">
                    <div class="form-contain-left">
                        <div class="add-item">
                            <label class="add-label">Matricule</label>
                            <input class="add-input" name="matricule" id="matricule" type="text">
                        </div>
                        <div class="add-item">
                            <label class="add-label">Year</label>
                            <input class="add-input" name="year" id="year" type="text">
                        </div>
                        <div class="add-item">
                            <label class="add-label">Date</label>
                            <input class="add-input" name="date_payment" id="date_payment" type="date">
                        </div>
                    </div>
                    <div class="form-contain-right">
                        <div class="add-item">
                            <label class="add-label">number month</label>
                            <input class="add-input" name="nbr_month" id="nbr_month" type="text">
                        </div>
                        <div class="add-item">
                            <label class="add-label">Session ID</label>
                            <input class="add-input" name="session_id" id="session_id" type="text">
                        </div>
                        <div class="add-item">
                            <input class="add-submit" type="submit" value="Add payment">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="org.project.projet_bourse.Model.PaymentModel" %><%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 27/06/2024
  Time: 08:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="static/payment.scss">
</head>
<body>
<div class="payment">
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
    <div class="payment-page">
        <div class="header">
          <div class="header-first">
              <div class="header-round"></div>
              <div class="icon-part">
                  <h2 class="titre">PAYMENTS</h2>
                  <div class="buttonWithIcon">
                      <div class="button-icon add"></div>
                      <span class="button-text">
                 <a href="/Projet_bourse_war_exploded/addPayment">Add new payment</a>
             </span>
                  </div>
              </div>
          </div>
        </div>
        <div class="payment-container">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Matricule</th>
                    <th>Year</th>
                    <th>Date payment</th>
                    <th>number months</th>
                    <th>Calendar</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<PaymentModel> payments = (List<PaymentModel>) request.getAttribute("paymentList");
                    if(payments!= null && !payments.isEmpty()) {
                        for (PaymentModel payment : payments) {
                %>
                <tr>
                    <td><%= payment.getPayment_id()%></td>
                    <td><%= payment.getMatricule()%></td>
                    <td><%= payment.getYear()%></td>
                    <td><%= payment.getDate_payment()%></td>
                    <td><%= payment.getNbr_month()%></td>
                    <td><%= payment.getSession_id()%></td>
                    <td class="actions-id">
                        <a href="/Projet_bourse_war_exploded/pdfGenerate?payment_id=<%=payment.getPayment_id()%>">Generate pdf</a>
                        <a href="/Projet_bourse_war_exploded/modifyPayment?payment_id=<%= payment.getPayment_id()%>">Edit</a>
                        <a href="/Projet_bourse_war_exploded/deletePayment?payment_id=<%= payment.getPayment_id()%>">Delete</a>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="8">No payment found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

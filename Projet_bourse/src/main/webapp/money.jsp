<%@ page import="org.project.projet_bourse.Model.MoneyModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: WINDOWS 10
  Date: 25/06/2024
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Money</title>
    <link rel="stylesheet" href="static/money.scss">
</head>
<body>
    <div class="money">
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
                <div class="sidebar-item active">
                    <div class="sidebar-icon active" id="calendar">i</div>
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
        <div class="money-page">
            <div class="header">
                <div class="header-first"><span class="money-title">MONEY</span></div>
                <div class="header-rounded"></div>
            </div>
            <div class="money-body">
                <div class="money-left">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Level</th>
                            <th>Amount</th>
                            <th>Equipments</th>
                            <th>ACTIONS</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% List<MoneyModel> moneys = (List<MoneyModel>) request.getAttribute("listMoney");
                            if (moneys!= null && !moneys.isEmpty()) {
                                for (MoneyModel money : moneys) { %>
                        <tr>
                            <td><%= money.getMoney_id()%></td>
                            <td><%= money.getLevel()%></td>
                            <td><%= money.getAmount()%></td>
                            <td><%= money.getEquipements()%></td>
                            <td class="actions-td">
                                <a href="/Projet_bourse_war_exploded/modifyMoney?money_id=<%= money.getMoney_id()%>">Edit</a>
                                <a href="/Projet_bourse_war_exploded/deleteMoney?money_id=<%= money.getMoney_id()%>">Delete</a>
                            </td>
                        </tr>
                        <% }
                        } else { %>
                        <tr>
                            <td colspan="8">No money record found.</td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="money-right">
                    <form class="form-money" action="addMoney" method="post">
                        <div class="inputMoney-item">
                            <label class="money-label" for="level">Level</label>
                            <select class="money-input" name="level" id="level">
                                <option value="L1">L1</option>
                                <option value="L2">L2</option>
                                <option value="L3">L3</option>
                                <option value="M1">M1</option>
                                <option value="M2">M2</option>
                            </select>
                        </div>
                        <div class="inputMoney-item">
                            <label class="money-label" for="amount">Amount</label>
                            <input class="money-input" name="amount" id="amount" type="number">
                        </div>
                        <div class="inputMoney-item">
                            <label class="money-label" for="equipements">Equipments</label>
                            <input class="money-input" name="equipements" id="equipements" type="number">
                        </div>
                        <input class="money-submit" type="submit" value="Submit record">
                    </form>
                    <form class="form-money" action="modifyMoney" method="post">
                        <input type="hidden" name="money_id" id="money_id" value="<%= request.getAttribute("money_id") %>">
                        <div class="inputMoney-item">
                            <label class="money-label" for="level">Level</label>
                            <select class="money-input" name="level">
                                <option value="L1">L1</option>
                                <option value="L2">L2</option>
                                <option value="L3">L3</option>
                                <option value="M1">M1</option>
                                <option value="M2">M2</option>
                            </select>
                        </div>
                        <div class="inputMoney-item">
                            <label class="money-label" for="amount">Amount</label>
                            <input class="money-input" name="amount" id="amount" type="number" value="<%= request.getAttribute("amount")%>">
                        </div>
                        <div class="inputMoney-item">
                            <label class="money-label" for="equipements">Equipments</label>
                            <input class="money-input" name="equipements" id="equipements" type="number" value="<%= request.getAttribute("equipements")%>">
                        </div>
                        <input class="money-submit" type="submit" value="Submit update">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

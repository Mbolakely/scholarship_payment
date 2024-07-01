package org.project.projet_bourse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.MoneyDao;
import org.project.projet_bourse.Model.MoneyModel;

import java.io.IOException;

@WebServlet(name = "modifyMoneyServlet", value = "/modifyMoney")
public class ModifyMoneyServlet extends HttpServlet {
    private static final long serialVerisonUID = 1L;
    private MoneyDao moneyDao;

    public void init() throws ServletException {
        System.out.println("print in modif money servlet");
        moneyDao = new MoneyDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String moneyIdParam = request.getParameter("money_id");
        if(moneyIdParam!= null && !moneyIdParam.trim().isEmpty()) {
            try {
                int money_id = Integer.parseInt(moneyIdParam);
                MoneyModel existingMoney;
                try {
                    existingMoney = moneyDao.getMoney(money_id);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("money.jsp");
                    request.setAttribute("money_id", existingMoney.getMoney_id());
                    request.setAttribute("level", existingMoney.getLevel());
                    request.setAttribute("amount", existingMoney.getAmount());
                    request.setAttribute("equipements", existingMoney.getEquipements());

                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Else part for getting money to update");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int money_id = Integer.parseInt(request.getParameter("money_id"));
            System.out.println("try part in doPost in editing money");

            String level = request.getParameter("level");
            int amount = Integer.parseInt(request.getParameter("amount"));
            int equipements = Integer.parseInt(request.getParameter("equipements"));

            System.out.println("id:" + money_id);
            System.out.println("level:" + level);
            System.out.println("amount:" + amount);
            System.out.println("equipments:" + equipements);

            MoneyModel newMoney = new MoneyModel(money_id, level, amount, equipements);
            moneyDao.updateMoney(newMoney);
            System.out.println(" voici le truc"+newMoney.getAmount());
            response.sendRedirect("money");
        } catch (Exception e) {
            System.out.println("Error while updating money record:"+ e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid money id format catch in doPost money edit");
        }
    }
}

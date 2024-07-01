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

@WebServlet(name = "addMoney", value = "/addMoney")
public class AddMoneyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MoneyDao moneyDao;

    public void init() throws ServletException {
        System.out.println("print addMoneyServlet");
        moneyDao = new MoneyDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    //    RequestDispatcher dispatcher = request.getRequestDispatcher("money.jsp");
    //    dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("try part in doPost adding new money record");

            String level = request.getParameter("level");

            String amount_param = request.getParameter("amount");
            int amount = Integer.parseInt(amount_param);

            String equipements_param = request.getParameter("equipements");
            int equipements = Integer.parseInt(equipements_param);

            MoneyModel newMoney = new MoneyModel(level, amount, equipements);
            moneyDao.insertMoney(newMoney);
            response.sendRedirect("money");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }


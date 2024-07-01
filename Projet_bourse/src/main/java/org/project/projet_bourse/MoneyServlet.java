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
import java.util.List;

@WebServlet(name = "moneyServlet", value = "/money")

public class MoneyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MoneyDao moneyDao;

    public void init() throws ServletException {
        System.out.println("print in money servlet list");
        moneyDao = new MoneyDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            System.out.println("try part in doGet showing money list");
            List<MoneyModel> listMoney = moneyDao.getAllMoney();
            request.setAttribute("listMoney", listMoney);
            System.out.println(listMoney.get(0).getLevel());
            RequestDispatcher dispatcher = request.getRequestDispatcher("money.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

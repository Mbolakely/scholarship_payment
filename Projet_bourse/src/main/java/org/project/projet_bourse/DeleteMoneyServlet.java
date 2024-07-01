package org.project.projet_bourse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.projet_bourse.Dao.MoneyDao;

import java.io.IOException;

@WebServlet(name = "deleteMoneyServlet", value = "/deleteMoney")

public class DeleteMoneyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String moneyIdParam = request.getParameter("money_id");
        try {
            int money_id = Integer.parseInt((moneyIdParam));
            MoneyDao moneyDao = new MoneyDao();
            boolean result = moneyDao.deleteMoney(money_id);
            response.sendRedirect("money");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
